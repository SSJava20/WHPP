/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.message;

import com.microsoft.windowsazure.services.core.Configuration;
import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusContract;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusService;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author NGAL
 */
@Stateless
public class MessageSender {

	final static String TOPIC_PATH = "web_receive";

	final static String SUB_NAME = "web";

	final static String SERVICEBUS_NAME = "WHPPServiceBus";

	final static String OWNER = "owner";

	final static String KEY = "OTUSJ7fJBhEqWGwFdunLLbZA/45AeMxeDcSkH+4O418=";

	static Configuration config;

	static ServiceBusContract serviceBusContract;

	@PostConstruct
	public void configureBus() {
		config = ServiceBusConfiguration.configureWithWrapAuthentication(SERVICEBUS_NAME, OWNER,
				KEY);

		serviceBusContract = ServiceBusService.create(config);
	}

	public void sendMessage(Message message) throws IOException, ServiceException {
		
		BrokeredMessage brokeredMessage = new BrokeredMessage();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream stream = new ObjectOutputStream(byteArrayOutputStream);

		stream.writeObject(message);

		brokeredMessage.setBody(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		serviceBusContract.sendTopicMessage(TOPIC_PATH, brokeredMessage);

	}
}
