/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Employee;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:17 PM
 */
@Local
public interface EmployeeFacadeLocal {

	void create(Employee employee);

	void edit(Employee employee);

	void remove(Employee employee);

	Employee find(Object id);

	List<Employee> findAll();

	List<Employee> findRange(int[] range);

	int count();

}
