/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.util;

import com.microsoft.windowsazure.services.core.Configuration;
import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusContract;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusService;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.serviceBus.models.GetQueueResult;
import com.microsoft.windowsazure.services.serviceBus.models.GetTopicResult;
import com.microsoft.windowsazure.services.serviceBus.models.QueueInfo;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveMode;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveSubscriptionMessageResult;
import com.microsoft.windowsazure.services.serviceBus.models.TopicInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.courses.whpp.worker.properties.PropHolder;

/**
 *
 * @author NGAL
 */
@Singleton
@Startup
public class MessageReceiver implements MessageReceiverLocal
{

    @EJB
    PropHolder holder;

    private static Configuration config;

    private static ServiceBusContract serviceBusContract;

    private TopicInfo topicInfo;

    private ReceiveMessageOptions options;

    @PostConstruct
    public void postConstruct()
    {

	   System.out.println("Construct");

	   try
	   {
		  config = ServiceBusConfiguration.configureWithWrapAuthentication(holder.SERVICEBUS_NAME, holder.SERVICEBUS_OWNER, holder.SERVICEBUS_KEY);
		  serviceBusContract = ServiceBusService.create(config);

		  GetTopicResult result;
		  result = serviceBusContract.getTopic(holder.SERVICEBUS_TOPIC);
		  topicInfo = result.getValue();

		  Logger.getLogger(MessageReceiver.class.getName()).log(Level.INFO, "MessageListener successfully loaded");
	   } catch (ServiceException ex)
	   {
		  Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
	   }
    }

    @Override
    public BrokeredMessage getMessage() throws ServiceException
    {
	   createOptions();
	   ReceiveSubscriptionMessageResult result = serviceBusContract.receiveSubscriptionMessage(holder.SERVICEBUS_TOPIC, holder.SERVICEBUS_SUBSCRIPTION, options);
	   return result.getValue();

    }

    @Override
    public void sendMessageToItself(BrokeredMessage message) throws ServiceException
    {
	   serviceBusContract.sendTopicMessage(holder.SERVICEBUS_TOPIC, message);
    }

    private void createOptions()
    {
	   options = new ReceiveMessageOptions();
	   options.setReceiveMode(ReceiveMode.RECEIVE_AND_DELETE);
    }
}
