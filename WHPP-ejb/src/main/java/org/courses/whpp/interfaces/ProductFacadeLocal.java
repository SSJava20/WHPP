/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Product;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:20 PM
 */
@Local
public interface ProductFacadeLocal {

	void create(Product product);

	void edit(Product product);

	void remove(Product product);

	Product find(Object id);

	List<Product> findAll();

	List<Product> findRange(int[] range);

	int count();

}
