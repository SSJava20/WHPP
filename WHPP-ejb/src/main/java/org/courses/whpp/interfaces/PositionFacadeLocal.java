/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Position;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:18 PM
 */
@Local
public interface PositionFacadeLocal {

	void create(Position position);

	void edit(Position position);

	void remove(Position position);

	Position find(Object id);

	List<Position> findAll();

	List<Position> findRange(int[] range);

	int count();

}
