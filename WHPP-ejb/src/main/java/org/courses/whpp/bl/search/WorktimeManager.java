/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.bl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.courses.whpp.entity.Worktime;
import org.courses.whpp.session.WorktimeFacade;
import org.courses.whpp.session.WorktimeFacade;

/**
 *
 * @author stvad
 */
@Stateless
public class WorktimeManager {

	private Collection<Worktime> activeRecords;

	/**
	 *
	 */
	@EJB
	private WorktimeFacade pwFacade;

	public void logIn(Integer id) {
		for (Worktime cw : getActiveRecords()) {
			if (cw.getEmpId().getId() == id) {
				logOut(cw);
			}
		}
	}

	public void logOut(Integer id) {
		for (Worktime cw : getActiveRecords()) {
			if (cw.getEmpId().getId() == id) {
				logOut(cw);
			}
		}
	}

	protected void logOut(Worktime tl) {
		Date curTime = new Date();
		if ((curTime.getTime() - tl.getIntime().getTime()) > 43200) {
			tl.setOuttime(new Date(tl.getIntime().getTime() + 43200));
		} else {
			tl.setOuttime(curTime);
		}

		getPwFacade().create(tl);
		getActiveRecords().remove(tl);
	}

	public WorktimeManager() {
		activeRecords = new ArrayList<Worktime>();
		pwFacade = new WorktimeFacade();
	}

	/**
	 * @return the pwFacade
	 */
	public WorktimeFacade getPwFacade() {
		return pwFacade;
	}

	/**
	 * @param pwFacade the pwFacade to set
	 */
	public void setPwFacade(WorktimeFacade pwFacade) {
		this.pwFacade = pwFacade;
	}

	/**
	 * @return the activeRecords
	 */
	public Collection<Worktime> getActiveRecords() {
		return activeRecords;
	}

	/**
	 * @param activeRecords the activeRecords to set
	 */
	public void setActiveRecords(Collection<Worktime> activeRecords) {
		this.activeRecords = activeRecords;
	}
}
