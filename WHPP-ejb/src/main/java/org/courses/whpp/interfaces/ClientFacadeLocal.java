/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.interfaces;

import java.util.List;
import javax.ejb.Local;
import org.courses.whpp.entity.Client;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:23:15 PM
 */
@Local
public interface ClientFacadeLocal {

	void create(Client client);

	void edit(Client client);

	void remove(Client client);

	Client find(Object id);

	List<Client> findAll();

	List<Client> findRange(int[] range);

	int count();

}
