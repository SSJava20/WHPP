/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.util;

import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NGAL
 */
public class AllTest
{

    static EJBContainer container;

    MessageReceiverLocal messageListener;

    List<BrokeredMessage> messages = new ArrayList<BrokeredMessage>();

    public AllTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
	   container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass()
    {
	   container.close();
    }

    @Before
    public void setUp() throws NamingException
    {
	   messageListener = (MessageReceiverLocal) container.getContext().lookup("java:global/classes/MessageListener");
	   messages.clear();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testSendMessageToItself() throws NamingException, ServiceException
    {
	   System.out.println("sendMessageToItself");

	   sendMessages();
    }

    /**
     * Test of getMessage method, of class MessageListener.
     */
    @Test
    public void testGetMessage() throws Exception
    {
	   System.out.println("getMessage");

	   sendMessages();

	   BrokeredMessage result = messageListener.getMessage();

	   assertEquals(messages.get(0).getLabel(), result.getLabel());
    }

    private void sendMessages() throws ServiceException
    {
	   for (int i = 0; i < 10; i++)
	   {
		  BrokeredMessage bm = new BrokeredMessage("Message number : " + i);
		  bm.setLabel("Some label");

		  messageListener.sendMessageToItself(bm);
		  messages.add(bm);
	   }
    }

    // now used
    private void areEquals(List<BrokeredMessage> l, List<BrokeredMessage> r)
    {
	   assertEquals(l.size(), r.size());

	   for (int i = 0; i < l.size(); i++)
	   {
		  assertEquals(l.get(i).getLabel(), r.get(i).getLabel());
	   }
    }
}