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
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author NGAL
 */
@Stateless
public class MessageSender {

	@EJB
	ServiceBusProperties properties;

	static Configuration config;

	static ServiceBusContract serviceBusContract;

	@PostConstruct
	public void configureBus() {
		config = ServiceBusConfiguration.configureWithWrapAuthentication(properties.SERVICEBUS_NAME, properties.OWNER,
				properties.KEY);

		serviceBusContract = ServiceBusService.create(config);
	}

	public void sendMessage(Message message) throws IOException, ServiceException {

		BrokeredMessage brokeredMessage = new BrokeredMessage();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream stream = new ObjectOutputStream(byteArrayOutputStream);

		stream.writeObject(message);

		brokeredMessage.setBody(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		serviceBusContract.sendTopicMessage(properties.TOPIC_PATH, brokeredMessage);

	}
}
