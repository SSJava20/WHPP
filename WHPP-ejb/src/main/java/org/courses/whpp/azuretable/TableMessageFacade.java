/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.azuretable;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.WarningMsg;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class TableMessageFacade {

	public static String WARNNING_MESSAGES_TABLE = "WarningMessages";

	@EJB
	TableStorage tableStorage;

	private TableStoragedClass prepareWMessageToStore(WarningMsg msg) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(msg);

		TableStoragedClass tsc = new TableStoragedClass(msg.getDriverId(), String.valueOf(msg.getId()), baos.toByteArray());
		return tsc;

	}

	public WarningMsg getWMessage(WarningMsg msg) throws IOException, ClassNotFoundException, StorageException {

		TableStoragedClass tsc = tableStorage.get(msg.getDriverId(), String.valueOf(msg.getId()), WARNNING_MESSAGES_TABLE);

		ByteArrayInputStream bais = new ByteArrayInputStream(tsc.getDecodedStoredString());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (WarningMsg) ois.readObject();

	}

	public List<WarningMsg> getWMesagesForDriver(String driverId) throws StorageException, StorageException, ClassNotFoundException, IOException {

		List<WarningMsg> list = tableStorage.get(driverId, WARNNING_MESSAGES_TABLE);
		return list;

	}

	public void create(WarningMsg msg) throws StorageException, IOException {
		tableStorage.save(prepareWMessageToStore(msg), WARNNING_MESSAGES_TABLE);

	}

	public void createOrReplace(WarningMsg msg) throws StorageException, IOException {
		tableStorage.saveOrReplace(prepareWMessageToStore(msg), WARNNING_MESSAGES_TABLE);
	}
}
