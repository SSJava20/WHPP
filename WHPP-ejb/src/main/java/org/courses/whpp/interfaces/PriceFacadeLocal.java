/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Price;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:19 PM
 */
@Local
public interface PriceFacadeLocal {

	void create(Price price);

	void edit(Price price);

	void remove(Price price);

	Price find(Object id);

	List<Price> findAll();

	List<Price> findRange(int[] range);

	int count();

}
