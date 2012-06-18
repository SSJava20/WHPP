/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.TicketItem;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:25 PM
 */
@Local
public interface TicketItemFacadeLocal {

	void create(TicketItem ticketItem);

	void edit(TicketItem ticketItem);

	void remove(TicketItem ticketItem);

	TicketItem find(Object id);

	List<TicketItem> findAll();

	List<TicketItem> findRange(int[] range);

	int count();

}
