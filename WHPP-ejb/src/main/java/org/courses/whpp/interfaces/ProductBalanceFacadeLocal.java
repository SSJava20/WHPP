/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.ProductBalance;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:20 PM
 */
@Local
public interface ProductBalanceFacadeLocal {

	void create(ProductBalance productBalance);

	void edit(ProductBalance productBalance);

	void remove(ProductBalance productBalance);

	ProductBalance find(Object id);

	List<ProductBalance> findAll();

	List<ProductBalance> findRange(int[] range);

	int count();

}
