/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Vacation;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:28 PM
 */
@Local
public interface VacationFacadeLocal {

	void create(Vacation vacation);

	void edit(Vacation vacation);

	void remove(Vacation vacation);

	Vacation find(Object id);

	List<Vacation> findAll();

	List<Vacation> findRange(int[] range);

	int count();

}
