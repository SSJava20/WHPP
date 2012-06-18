/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Category;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:14 PM
 */
@Local
public interface CategoryFacadeLocal {

	void create(Category category);

	void edit(Category category);

	void remove(Category category);

	Category find(Object id);

	List<Category> findAll();

	List<Category> findRange(int[] range);

	int count();

}
