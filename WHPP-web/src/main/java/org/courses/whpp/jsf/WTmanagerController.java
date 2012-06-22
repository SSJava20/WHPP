/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.jsf;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.courses.whpp.jsf.util.JsfUtil;
import org.courses.whpp.session.WorkTimeManager;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com> Created on Jun 20, 2012, 4:50:45
 * PM
 */
@ManagedBean(name = "wtmanagerController")
@SessionScoped
public class WTmanagerController implements Serializable {

	private static final long serialVersionUID = 1L;

	private int empId;

	@EJB
	private org.courses.whpp.session.WorkTimeManager ejbFacade;

	public WTmanagerController() {
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int EmpId) {
		this.empId = EmpId;
	}

	private WorkTimeManager getFacade() {
		return ejbFacade;
	}

	public String logIn() {
		try {
			getFacade().logIn(empId);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("WorktimeManager_in_suscess"));
			return "Manager";
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public String logOut() {
		try {
			getFacade().logOut(empId);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("WorktimeManager_out_suscess"));
			return "Manager";
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
			return null;
		}
	}
}
