/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Property;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:21 PM
 */
@Local
public interface PropertyFacadeLocal {

	void create(Property property);

	void edit(Property property);

	void remove(Property property);

	Property find(Object id);

	List<Property> findAll();

	List<Property> findRange(int[] range);

	int count();

}
