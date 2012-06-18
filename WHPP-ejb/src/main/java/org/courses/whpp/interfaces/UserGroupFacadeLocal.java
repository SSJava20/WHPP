/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.UserGroup;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:26 PM
 */
@Local
public interface UserGroupFacadeLocal {

	void create(UserGroup userGroup);

	void edit(UserGroup userGroup);

	void remove(UserGroup userGroup);

	UserGroup find(Object id);

	List<UserGroup> findAll();

	List<UserGroup> findRange(int[] range);

	int count();

}
