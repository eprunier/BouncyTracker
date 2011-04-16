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

package com.bouncytracker.domain.model;

import java.util.HashMap;
import java.util.Map;

public enum StoryCategory {

	DEFECT(0),
	FEATURE(1),
	TECHNICAL(2);
	
	private static final Map<Integer, StoryCategory> CATEGORY_MAP = new HashMap<Integer, StoryCategory>();
	
	static {
		for (StoryCategory category : StoryCategory.values()) {
			CATEGORY_MAP.put(category.getCategory(), category);
		}
	}
	
	private final int category;
	
	private StoryCategory(int category) {
		this.category = category;
	}
	
	public int getCategory() {
		return category;
	}

	public static Map<Integer, StoryCategory> getCategoryMap() {
		return CATEGORY_MAP;
	}
	
}
