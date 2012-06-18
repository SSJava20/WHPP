/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Routepoint;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:23 PM
 */
@Local
public interface RoutepointFacadeLocal {

	void create(Routepoint routepoint);

	void edit(Routepoint routepoint);

	void remove(Routepoint routepoint);

	Routepoint find(Object id);

	List<Routepoint> findAll();

	List<Routepoint> findRange(int[] range);

	int count();

}
