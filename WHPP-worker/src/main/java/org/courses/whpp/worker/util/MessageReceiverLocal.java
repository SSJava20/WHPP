/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.util;

import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NGAL
 */
@Local
public interface MessageReceiverLocal
{

    public BrokeredMessage getMessage() throws com.microsoft.windowsazure.services.core.ServiceException;

    public void sendMessageToItself(com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage message) throws com.microsoft.windowsazure.services.core.ServiceException;
}
