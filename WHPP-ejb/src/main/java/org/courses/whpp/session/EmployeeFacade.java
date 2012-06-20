/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.courses.whpp.entity.Employee;
import org.courses.whpp.entity.Worktime;

import java.util.List;

/**
 * @author Roman Kostyrko <nubaseg@gmail.com>
 *         Created on Jun 13, 2012, 8:11:39 PM
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee>
{
    @PersistenceContext(unitName = "org.courses_WHPP-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EmployeeFacade()
    {
        super(Employee.class);
    }

    public Employee findById(Integer id)
    {
        return (Employee) em.createNamedQuery("Employee.findById").setParameter("id", id).getSingleResult();
    }

}
