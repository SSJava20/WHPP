package org.courses.whpp.jsf;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.courses.whpp.entity.Coords;
import org.courses.whpp.jsf.util.JsfUtil;
import org.courses.whpp.jsf.util.PaginationHelper;
import org.courses.whpp.session.CoordsFacade;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name = "coordsController")
@SessionScoped
public class CoordsController implements Serializable {

	private MapModel mapModel;

	private Coords current;

	private DataModel items = null;

	private String latlng;

	@EJB
	private org.courses.whpp.session.CoordsFacade ejbFacade;

	private PaginationHelper pagination;

	private int selectedItemIndex;

	public CoordsController() {
	}

	public Coords getSelected() {
		if (current == null) {
			current = new Coords();
			selectedItemIndex = -1;
		}
		return current;
	}

	private CoordsFacade getFacade() {
		return ejbFacade;
	}

	public String getLatlng() {
		latlng = current.getLatitude() + "," + current.getLongitude();
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public PaginationHelper getPagination() {
		if (pagination == null) {
			pagination = new PaginationHelper(10) {
				@Override
				public int getItemsCount() {
					return getFacade().count();
				}

				@Override
				public DataModel createPageDataModel() {
					return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
				}
			};
		}
		return pagination;
	}

	public void onPointSelect(PointSelectEvent event) {
		current.setLatitude(event.getLatLng().getLat());
		current.setLongitude(event.getLatLng().getLng());
	}

	public String prepareList() {
		recreateModel();
		return "List";
	}

	public String prepareView() {
		current = (Coords) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
		return "View";
	}

	public String prepareCreate() {
		current = new Coords();
		current.setId(1);
		selectedItemIndex = -1;
		return "Create";
	}

	public String create() {
		try {
			getFacade().create(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CoordsCreated"));
			return prepareCreate();
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public String prepareEdit() {
		current = (Coords) getItems().getRowData();
		System.out.println(this.getLatlng());
		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
		return "Edit";
	}

	public String update() {
		try {
			getFacade().edit(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CoordsUpdated"));
			return "View";
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public String destroy() {
		current = (Coords) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
		performDestroy();
		recreatePagination();
		recreateModel();
		return "List";
	}

	public String destroyAndView() {
		performDestroy();
		recreateModel();
		updateCurrentItem();
		if (selectedItemIndex >= 0) {
			return "View";
		} else {
			// all items were removed - go back to list
			recreateModel();
			return "List";
		}
	}

	private void performDestroy() {
		try {
			getFacade().remove(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CoordsDeleted"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
		}
	}

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

	public DataModel getItems() {
		if (items == null) {
			items = getPagination().createPageDataModel();
		}
		return items;
	}

	public MapModel getMapModel() {
		mapModel = new DefaultMapModel();


		LatLng coord1 = new LatLng(current.getLatitude(), current.getLongitude());

		//Basic marker
		mapModel.addOverlay(new Marker(coord1, current.getAddress()));

		return mapModel;
	}

	private void recreateModel() {
		items = null;
	}

	private void recreatePagination() {
		pagination = null;
	}

	public String next() {
		getPagination().nextPage();
		recreateModel();
		return "List";
	}

	public String previous() {
		getPagination().previousPage();
		recreateModel();
		return "List";
	}

	public SelectItem[] getItemsAvailableSelectMany() {
		return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
	}

	public SelectItem[] getItemsAvailableSelectOne() {
		return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
	}

	@FacesConverter(forClass = Coords.class)
	public static class CoordsControllerConverter implements Converter {

		public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			CoordsController controller = (CoordsController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "coordsController");
			return controller.ejbFacade.find(getKey(value));
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
			if (object instanceof Coords) {
				Coords o = (Coords) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Coords.class.getName());
			}
		}
	}
}
