/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.session;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.courses.whpp.entity.Employee;
import org.courses.whpp.entity.Worktime;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 */
@Stateless
public class WorkTimeManager  {

	@EJB
	private EmployeeFacade employeeFacade;

	@EJB
	private WorktimeFacade wtFacade;

	@PersistenceContext(unitName = "org.courses_WHPP-ejb_ejb_1.0-SNAPSHOTPU")
	private EntityManager em;

	public WorkTimeManager() {
	}


	public void logIn(Integer EmployeeId) {
		Employee employeeForId = employeeFacade.find(EmployeeId);
		List<Worktime> worktimeForId = wtFacade.findOpenedByEmployeeId(employeeForId);
		for (Worktime wt : worktimeForId) {
			logOut(wt);
		}
		wtFacade.create(new Worktime(new Date(), null, employeeForId));
	}

	public void logOut(Integer EmployeeId) {
		Employee employeeForId = employeeFacade.find(EmployeeId);
		if (employeeForId == null) {
			throw new IllegalStateException("Emp is not found");
		}
		List<Worktime> worktimeForId = wtFacade.findOpenedByEmployeeId(employeeForId);
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

		wtFacade.edit(tl);
	}
}
