/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.courses.whpp.utils;

import java.security.MessageDigest;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 9:34:47 PM
 */
public class PasswordHash {

	private static final String driver = "com.mysql.jdbc.Driver";

	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/wh1";

	private static final String userSql = "insert into usr (userid,password) values(?, ?)";

	private static final String groupSql = "insert into grouptable values(?, ?)";

	private static final char[] HEXADECIMAL = {'0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String codePassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();

		byte[] bytes = md.digest(password.getBytes());
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			int low = (int) (bytes[i] & 0x0f);
			int high = (int) ((bytes[i] & 0xf0) >> 4);
			sb.append(HEXADECIMAL[high]);
			sb.append(HEXADECIMAL[low]);
		}
		return sb.toString();
	}

}
