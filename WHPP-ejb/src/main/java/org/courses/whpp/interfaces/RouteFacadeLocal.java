/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Route;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:22 PM
 */
@Local
public interface RouteFacadeLocal {

	void create(Route route);

	void edit(Route route);

	void remove(Route route);

	Route find(Object id);

	List<Route> findAll();

	List<Route> findRange(int[] range);

	int count();

}
