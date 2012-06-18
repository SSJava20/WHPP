/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.UserGroupRelation;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:27 PM
 */
@Local
public interface UserGroupRelationFacadeLocal {

	void create(UserGroupRelation userGroupRelation);

	void edit(UserGroupRelation userGroupRelation);

	void remove(UserGroupRelation userGroupRelation);

	UserGroupRelation find(Object id);

	List<UserGroupRelation> findAll();

	List<UserGroupRelation> findRange(int[] range);

	int count();

}
