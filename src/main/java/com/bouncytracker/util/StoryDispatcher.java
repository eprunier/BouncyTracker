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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bouncytracker.domain.model.Story;
import com.bouncytracker.domain.model.StoryStatus;

public class StoryDispatcher {

	private final List<Story> todo = new ArrayList<Story>();
	private final List<Story> currentStories = new ArrayList<Story>();
	private final List<Story> completedStories = new ArrayList<Story>();

	public void dispatch(List<Story> projectStories) {
		for (Story story : projectStories) {
			StoryStatus storyStatus = getStoryStatus(story);
			switch (storyStatus) {
			case STARTED:
				currentStories.add(story);
				break;
			case COMPLETED:
				completedStories.add(story);
				break;
			default:
				todo.add(story);
				break;
			}
		}
		
		sortTodoByPriority();
	}
	
	private StoryStatus getStoryStatus(Story story) {
		return StoryStatus.getStatusMap().get(story.getStatus());
	}

	private void sortTodoByPriority() {
		Collections.sort(todo, new Comparator<Story>() {
			@Override 
			public int compare(Story story1, Story story2) {
				Integer story1Priority = story1.getPriority();
				Integer story2Priority = story2.getPriority();
				return story1Priority.compareTo(story2Priority);
			}
		});
	}
	
	public List<Story> getTodo() {
		return todo;
	}

	public List<Story> getCurrentStories() {
		return currentStories;
	}

	public List<Story> getCompletedStories() {
		return completedStories;
	}

}
