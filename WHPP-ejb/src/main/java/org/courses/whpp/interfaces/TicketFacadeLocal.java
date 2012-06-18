/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Ticket;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:24 PM
 */
@Local
public interface TicketFacadeLocal {

	void create(Ticket ticket);

	void edit(Ticket ticket);

	void remove(Ticket ticket);

	Ticket find(Object id);

	List<Ticket> findAll();

	List<Ticket> findRange(int[] range);

	int count();

}
