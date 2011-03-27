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

import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;

public class ConfigUtil {

	private static final String BUNDLE_NAME = "messages";
	
	public static void configDateBinder(WebDataBinder binder) {
		binder.registerCustomEditor(
				Date.class, 
				new CustomDateEditor(DateFormat.getDateInstance(DateFormat.SHORT), true)
		);
	}
	
	public static String getMessage(String key) {
		return ResourceBundle.getBundle(BUNDLE_NAME).getString(key);
	}
	
}
