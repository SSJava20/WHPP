package org.courses.whpp.jsf;

import org.courses.whpp.entity.Route;
import org.courses.whpp.jsf.util.JsfUtil;
import org.courses.whpp.jsf.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.courses.mobileentity.entity.CoordsXML;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.RoutepointXML;
import org.courses.whpp.azuretable.TableRouteFacade;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

@ManagedBean(name = "trackingController")
@SessionScoped
public class TrackingController implements Serializable {

	private MapModel mapModel;

	private RouteXML current;

	private String latlng;

	private DataModel items = null;

	@EJB
	private org.courses.whpp.azuretable.TableRouteFacade ejbFacade;

	private PaginationHelper pagination;

	private int selectedItemIndex;

	public TrackingController() {
	}

	public RouteXML getSelected() {
		if (current == null) {
			current = new RouteXML();
			selectedItemIndex = -1;
		}
		return current;
	}

	private TableRouteFacade getFacade() {
		return ejbFacade;
	}

	public PaginationHelper getPagination() throws Exception {
		if (pagination == null) {
			pagination = new PaginationHelper(10) {
				@Override
				public int getItemsCount() {
					return getFacade().count();
				}

				@Override
				public DataModel createPageDataModel() {
					List<RouteXML> list = getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
					return new ListDataModel(list);
				}
			};
		}
		return pagination;
	}

	public String prepareList() {
		recreateModel();
		return "List";
	}

	public String prepareView() throws Exception {
		current = (RouteXML) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
		return "View";
	}

//	public String prepareCreate() {
//		current = new RouteXML();
//		current.setId("-1");
//		selectedItemIndex = -1;
//		return "Create";
//	}
//	public String prepareEdit() {
//		current = (RouteXML) getItems().getRowData();
//		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//		return "Edit";
//	}
//	public String destroy() {
//		current = (Route) getItems().getRowData();
//		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//		performDestroy();
//		recreatePagination();
//		recreateModel();
//		return "List";
//	}
//	public String destroyAndView() {
//		performDestroy();
//		recreateModel();
//		updateCurrentItem();
//		if (selectedItemIndex >= 0) {
//			return "View";
//		} else {
//			// all items were removed - go back to list
//			recreateModel();
//			return "List";
//		}
//	}
//	private void performDestroy() {
//		try {
//			getFacade().remove(current);
//			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RouteDeleted"));
//		} catch (Exception e) {
//			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//		}
//	}
	private void updateCurrentItem() {
		int count = getFacade().count();
		if (selectedItemIndex >= count) {
			// selected index cannot be bigger than number of items:
			selectedItemIndex = count - 1;
			// go to previous page if last page disappeared:
			if (pagination.getPageFirstItem() >= count) {
				pagination.previousPage();
			}
		}
		if (selectedItemIndex >= 0) {
			current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
		}
	}

	public MapModel getMapModel() {
		updateCurrentItem();
		mapModel = new DefaultMapModel();

		if (current.getRoutepointList() == null) {
			return mapModel;
		}

		for (int i = 0; i < current.getRoutepointList().size(); i++) {
			RoutepointXML point = current.getRoutepointList().get(i);
			CoordsXML coords = point.getCoordsId();

			LatLng ll = new LatLng(coords.getLatitude(), coords.getLongitude());

			Circle circle = new Circle(ll, 1000);

			if (point.isIsPassed()) {
				circle.setStrokeColor("#00ff00");
				circle.setFillColor("#00ff00");
				circle.setStrokeOpacity(0.7);
				circle.setFillOpacity(0.7);

			} else {
				circle.setStrokeColor("#d93c3c");
				circle.setFillColor("#d93c3c");
				circle.setFillOpacity(0.5);
			}
			circle.setData(point);
			mapModel.addOverlay(circle);
		}
		return mapModel;
	}

	public void onCircleSelect(OverlaySelectEvent event) {
		Circle s = (Circle) event.getSource();
		RoutepointXML routepointXML = (RoutepointXML) s.getData();
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "The point passed at : " + routepointXML.getPassedTime(), null));
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage("messagePanel", message);
	}

	public String getLatlng() {
		updateCurrentItem();
		if (current.getRoutepointList() != null && !current.getRoutepointList().isEmpty()) {
			CoordsXML coords = current.getRoutepointList().get(0).getCoordsId();
			latlng = coords.getLatitude() + "," + coords.getLongitude();
		} else {
			latlng = "0,0";
		}
		return latlng;
	}

	public DataModel getItems() throws Exception {
		if (items == null) {
			items = getPagination().createPageDataModel();
		}
		return items;
	}

	private void recreateModel() {
		items = null;
	}

	private void recreatePagination() {
		pagination = null;
	}

	public String next() throws Exception {
		getPagination().nextPage();
		recreateModel();
		return "List";
	}

	public String previous() throws Exception {
		getPagination().previousPage();
		recreateModel();
		return "List";
	}

	public SelectItem[] getItemsAvailableSelectMany() {
		return JsfUtil.getSelectItems(ejbFacade.getAllRoutes(), false);
	}

	public SelectItem[] getItemsAvailableSelectOne() {
		return JsfUtil.getSelectItems(ejbFacade.getAllRoutes(), true);




	}

	@FacesConverter(forClass = Route.class)
	public static class RouteControllerConverter implements Converter {

		public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			TrackingController controller = (TrackingController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "trackingController");
			return controller.ejbFacade.getRouteForDriver(String.valueOf(getKey(value)));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuffer sb = new StringBuffer();
			sb.append(value);
			return sb.toString();
		}

		public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Route) {
				Route o = (Route) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Route.class.getName());
			}
		}
	}
}
