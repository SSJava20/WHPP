/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.message;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author NGAL
 */
@Singleton 
@LocalBean
public class ServiceBusProperties {

	public final String TOPIC_PATH = "web_receive";

	public final String SUB_NAME = "web";

	public final String SERVICEBUS_NAME = "WHPPServiceBus";

	public final String OWNER = "owner";

	public final String KEY = "OTUSJ7fJBhEqWGwFdunLLbZA/45AeMxeDcSkH+4O418=";
}
