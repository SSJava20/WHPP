/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.azuretable;

import com.microsoft.windowsazure.services.table.client.TableServiceEntity;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author NGAL
 */
public class TableStoragedClass extends TableServiceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String storedString = "";

	public TableStoragedClass() {
	}

	public TableStoragedClass(String pKey, String rKey, byte[] arr) {
		this.partitionKey = pKey;
		this.rowKey = rKey;

		convertStoragedString(arr);
	}

	private void convertStoragedString(byte[] arr) {
		this.storedString = Base64.encodeBase64String(arr);
	}

	public void setStoredString(String str) {
		this.storedString = str;
	}

	public String getStoredString() {
		return storedString;
	}

	public byte[] getDecodedStoredString() {
		return Base64.decodeBase64(storedString);
	}
}
