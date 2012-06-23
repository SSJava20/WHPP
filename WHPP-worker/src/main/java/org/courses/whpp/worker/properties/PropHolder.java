/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.properties;

import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author NGAL
 */
@Stateless
@Singleton
public class PropHolder
{

    public final String STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=http;" + "AccountName=whpptrackingstorage;"
		  + "AccountKey=tHG1wAksgz1UIdyLqPiOy4T0U6QaTOBIGb2eX139uv2ut/GS7lR31TL1c25J6F3YhaDxB0ZwtfJYuwnAcrnVeA==";

    public final String SERVICEBUS_KEY = "OTUSJ7fJBhEqWGwFdunLLbZA/45AeMxeDcSkH+4O418=";

    public final String SERVICEBUS_NAME = "WHPPServiceBus";

    public final String SERVICEBUS_OWNER = "owner";

    public final String SERVICEBUS_TOPIC = "web_receive";

    public final String SERVICEBUS_SUBSCRIPTION = "web";

}
