/** 
 * Copyright (C) 2011  Eric Prunier
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.bouncytracker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.codec.Hex;

public class PasswordUtil {

	private static PasswordUtil passwordDigester = new PasswordUtil();

	private final MessageDigest digester;

	public static PasswordUtil getInstance() {
		return passwordDigester;
	}

	private PasswordUtil() {
		try {
			digester = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(
					ConfigUtil.getMessage(Message.ERROR_INTERNAL.getKey()),
					ex
			);
		}
	}

	public String digest(String password) {
		digester.update(password.getBytes());
		byte[] result = digester.digest();
		return new String(Hex.encode(result));
	}
}
