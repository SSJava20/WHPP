/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.azuretable;

import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class TableStorage {

	public static final String storageConnectionString =
			"DefaultEndpointsProtocol=http;"
			+ "AccountName=whpptrackingstorage;"
			+ "AccountKey=tHG1wAksgz1UIdyLqPiOy4T0U6QaTOBIGb2eX139uv2ut/GS7lR31TL1c25J6F3YhaDxB0ZwtfJYuwnAcrnVeA==";

	CloudTableClient tableClient = null;

	CloudStorageAccount storageAccount = null;

	public TableStorage() throws URISyntaxException, InvalidKeyException {

		storageAccount = CloudStorageAccount.parse(storageConnectionString);
		tableClient = storageAccount.createCloudTableClient();
	}

	public void save(TableStoragedClass storagedClass, String tableName) throws StorageException {

		TableOperation insert = TableOperation.insert(storagedClass);
		tableClient.execute(tableName, insert);
	}

	public void saveOrReplace(TableStoragedClass storagedClass, String tableName) throws StorageException {

		TableOperation insert = TableOperation.insertOrReplace(storagedClass);
		tableClient.execute(tableName, insert);
	}

	public TableStoragedClass get(String partitionKey, String rowKey, String tableName) throws StorageException, IOException, ClassNotFoundException {

		TableOperation retrieve = TableOperation.retrieve(partitionKey, rowKey, TableStoragedClass.class);
		return tableClient.execute(tableName, retrieve).getResultAsType();

	}

	public List get(String partitioKey, String tableName) throws StorageException, IOException, ClassNotFoundException {

		ArrayList list = new ArrayList();

		String partitionFilter = TableQuery.generateFilterCondition(
				TableConstants.PARTITION_KEY,
				TableQuery.QueryComparisons.EQUAL,
				partitioKey);

		TableQuery<TableStoragedClass> partitionQuery = TableQuery.from(tableName, TableStoragedClass.class).where(partitionFilter);

		for (TableStoragedClass tsc : tableClient.execute(partitionQuery)) {

			ByteArrayInputStream bais = new ByteArrayInputStream(tsc.getDecodedStoredString());
			ObjectInputStream ois = new ObjectInputStream(bais);
			list.add(ois.readObject());
		}

		return list;
	}
}
