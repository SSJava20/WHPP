/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Worktime;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:29 PM
 */
@Local
public interface WorktimeFacadeLocal {

	void create(Worktime worktime);

	void edit(Worktime worktime);

	void remove(Worktime worktime);

	Worktime find(Object id);

	List<Worktime> findAll();

	List<Worktime> findRange(int[] range);

	int count();

	public java.util.List<org.courses.whpp.entity.Worktime> findOpenedByEmployeeId(org.courses.whpp.entity.Employee EmployeeId);

	public void logIn(java.lang.Integer EmployeeId);

	public void logOut(java.lang.Integer EmployeeId);

}
