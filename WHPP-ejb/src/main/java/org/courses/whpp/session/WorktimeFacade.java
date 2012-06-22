/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.courses.whpp.entity.Employee;
import org.courses.whpp.entity.Worktime;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 * @author Roman Kostyrko <nubaseg@gmail.com> Created on Jun 13, 2012, 8:11:44
 * PM
 */
@Stateless
public class WorktimeFacade extends AbstractFacade<Worktime> {

	@EJB
	private EmployeeFacade employeeFacade;

	@PersistenceContext(unitName = "org.courses_WHPP-ejb_ejb_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public WorktimeFacade() {
		super(Worktime.class);
		employeeFacade = new EmployeeFacade();
	}

	public List<Worktime> findOpenedByEmployeeId(Employee EmployeeId) {
		return em.createNamedQuery("Worktime.findOpenedByEmployeeId").setParameter("EmployeeId", EmployeeId).getResultList();
	}

	public void logIn(Integer EmployeeId) {
		Employee employeeForId = employeeFacade.find(EmployeeId);
		List<Worktime> worktimeForId = findOpenedByEmployeeId(employeeForId);
		for (Worktime wt : worktimeForId) {
			logOut(wt);
		}
		this.create(new Worktime(new Date(), null, employeeForId));
	}

	public void logOut(Integer EmployeeId) {
		Employee employeeForId = employeeFacade.find(EmployeeId);
		if (employeeForId == null) {
			throw new IllegalStateException("Emp is not found");
		}
		List<Worktime> worktimeForId = findOpenedByEmployeeId(employeeForId);
		for (Worktime wt : worktimeForId) {
			logOut(wt);
		}
	}

	protected void logOut(Worktime tl) {
		Date curTime = new Date();
		if ((curTime.getTime() - tl.getIntime().getTime()) > 43200) {
			tl.setOuttime(new Date(tl.getIntime().getTime() + 43200));
		} else {
			tl.setOuttime(curTime);
		}

		this.edit(tl);
	}
}
