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

public enum StoryStatus {

	CREATED(10),
	STARTED(20),
	COMPLETED(30);

	private static Map<Integer, StoryStatus> statusMap = new HashMap<Integer, StoryStatus>();
	
	static {
		for (StoryStatus storyStatus : StoryStatus.values()) {
			statusMap.put(storyStatus.getStatus(), storyStatus);
		}
	}
	
	private final int status;
	
	private StoryStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public static Map<Integer, StoryStatus> getStatusMap() {
		return statusMap;
	}

}
