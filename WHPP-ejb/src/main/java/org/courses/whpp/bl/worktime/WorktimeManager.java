/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.bl.worktime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.courses.whpp.entity.Worktime;
import org.courses.whpp.session.WorktimeFacade;

/**
 *
 * @author stvad
 */
public class WorktimeManager 
{
    protected Collection<Worktime> activeRecords;
    protected WorktimeFacade pwFacade;
    
    public void logIn(Integer id)
    {
        for(Worktime cw:activeRecords)
        {
            if(cw.getEmpId().getId() == id)
            {
                logOut(cw);
            }
        }
        
        activeRecords.add(new Worktime(id, new Date(), null));       
        
    }
    
    public void logOut(Integer id)
    {
        for(Worktime cw:activeRecords)
        {
            if(cw.getEmpId().getId() == id)
            {
                logOut(cw);
            }
        }
    }
    
    protected void logOut(Worktime tl)
    {
        Date curTime = new Date();
        if((curTime.getTime() - tl.getIntime().getTime()) > 43200)
        {
            tl.setOuttime(new Date(tl.getIntime().getTime()+43200));
        }
        else
        {
            tl.setOuttime(curTime);
        }
        
        pwFacade.create(tl);
        activeRecords.remove(tl);
    }

    public WorktimeManager()
    {
        activeRecords = new ArrayList<Worktime>();
        pwFacade = new WorktimeFacade();
    }
}
