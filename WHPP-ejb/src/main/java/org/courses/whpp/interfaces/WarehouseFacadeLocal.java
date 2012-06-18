/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Warehouse;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:29 PM
 */
@Local
public interface WarehouseFacadeLocal {

	void create(Warehouse warehouse);

	void edit(Warehouse warehouse);

	void remove(Warehouse warehouse);

	Warehouse find(Object id);

	List<Warehouse> findAll();

	List<Warehouse> findRange(int[] range);

	int count();

}
