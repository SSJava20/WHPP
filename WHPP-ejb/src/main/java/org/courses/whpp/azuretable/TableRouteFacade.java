/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.azuretable;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.whpp.entity.Route;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class TableRouteFacade {

	public static String ROUTE_PARTITION_KEY = "Route";

	public static String ROUTE_TABLE = "testTable";

	@EJB
	TableStorage tableStorage;

	private TableStoragedClass prepareRouteToStore(RouteXML r, String driverId) {
		ObjectOutputStream oos = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(r);
			TableStoragedClass tsc = new TableStoragedClass(ROUTE_PARTITION_KEY, driverId, baos.toByteArray());
			return tsc;
		} catch (IOException ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public RouteXML getRouteForDriver(String driverId) {
		try {
			TableStoragedClass tsc = tableStorage.get(ROUTE_PARTITION_KEY, driverId, ROUTE_TABLE);

			ByteArrayInputStream bais = new ByteArrayInputStream(tsc.getDecodedStoredString());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (RouteXML) ois.readObject();
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public List<RouteXML> getAllRoutes() {
		try {
			List<RouteXML> list = tableStorage.get(ROUTE_PARTITION_KEY, ROUTE_TABLE);

			return list;
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public int count() {
		try {
			return tableStorage.get(ROUTE_PARTITION_KEY, ROUTE_TABLE).size();
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	public List<RouteXML> findRange(int[] range) {
		List<RouteXML> list = null;
		try {
			list = tableStorage.get(ROUTE_PARTITION_KEY, ROUTE_TABLE);
			return list.subList(range[0], range[range.length - 1]);
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
			return list.subList(range[0], list.size() - 1);
		}
	}

	public void create(RouteXML routeXML, String driverId) {
		try {
			tableStorage.save(prepareRouteToStore(routeXML, driverId), ROUTE_TABLE);
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void createOrReplace(RouteXML routeXML, String driverId) {
		try {
			tableStorage.saveOrReplace(prepareRouteToStore(routeXML, driverId), ROUTE_TABLE);
		} catch (Exception ex) {
			Logger.getLogger(TableRouteFacade.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
