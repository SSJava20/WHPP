/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Coords;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:16 PM
 */
@Local
public interface CoordsFacadeLocal {

	void create(Coords coords);

	void edit(Coords coords);

	void remove(Coords coords);

	Coords find(Object id);

	List<Coords> findAll();

	List<Coords> findRange(int[] range);

	int count();

}
