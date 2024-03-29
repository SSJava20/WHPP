/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.courses.whpp.entity.Position;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:11:39 PM
 */
@Stateless
public class PositionFacade extends AbstractFacade<Position> {
	@PersistenceContext(unitName = "org.courses_WHPP-ejb_ejb_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PositionFacade() {
		super(Position.class);
	}

}
