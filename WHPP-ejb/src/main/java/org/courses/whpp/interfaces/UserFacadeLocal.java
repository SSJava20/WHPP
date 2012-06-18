/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.User;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:26 PM
 */
@Local
public interface UserFacadeLocal {

	void create(User user);

	void edit(User user);

	void remove(User user);

	User find(Object id);

	List<User> findAll();

	List<User> findRange(int[] range);

	int count();

}
