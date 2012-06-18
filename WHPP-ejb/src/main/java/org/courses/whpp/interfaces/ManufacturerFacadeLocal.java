/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Manufacturer;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:17 PM
 */
@Local
public interface ManufacturerFacadeLocal {

	void create(Manufacturer manufacturer);

	void edit(Manufacturer manufacturer);

	void remove(Manufacturer manufacturer);

	Manufacturer find(Object id);

	List<Manufacturer> findAll();

	List<Manufacturer> findRange(int[] range);

	int count();

}
