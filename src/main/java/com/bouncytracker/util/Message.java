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

public enum Message {

	ERROR_EMPTY_PASSWORD("user.error.password.empty"),
	ERROR_PASSWORD_VERIFY("user.error.verifyPassword"),
	ERROR_INTERNAL("error.internal");
	
	private final String key;
	
	private Message(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return key;
	}
	
	public String getKey() {
		return key;
	}
	
}
