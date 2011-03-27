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

package com.bouncytracker.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouncytracker.domain.model.Project;
import com.bouncytracker.domain.model.Story;
import com.bouncytracker.domain.model.StoryStatus;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.domain.repository.ProjectRepository;
import com.bouncytracker.domain.repository.StoryRepository;

@Service
@Transactional
public class ProjectManager {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private StoryRepository storyRepository;

	@Transactional(readOnly = true)
	public List<Project> listUserProjects(User user) {
		return projectRepository.listProjectsForUser(user);
	}
	
	@Transactional(readOnly = true)
	public List<Project> listUserProjectsWithStartedStories(User user) {
		return projectRepository.listProjectsWithStartedStoriesForUser(user);
	}

	public void createProject(Project newProject) {
		Project project = projectRepository.getProject(newProject.getName());
		if (project == null) {
			projectRepository.saveProject(newProject);
		}
	}

	@Transactional(readOnly = true)
	public Project loadProjectWithStories(String id) {
		Project project = projectRepository.loadProjectWithStories(id);
		return project;
	}

	@Transactional(readOnly = true)
	public Project loadProject(String projectId) {
		return projectRepository.getProject(projectId);
	}

	public void updateProject(Project project) {
		projectRepository.updateProject(project);
	}

	public void deleteProject(String id) {
		Project project = projectRepository.getProject(id);
		projectRepository.deleteProject(project);
	}

	public void createStory(Story story) {
		storyRepository.saveStory(story);
	}

	@Transactional(readOnly = true)
	public Story loadStory(String id) {
		return storyRepository.getStory(id);
	}
	
	@Transactional(readOnly = true)
	public Story loadStoryWithProject(String id) {
		return storyRepository.getStoryWithProject(id);
	}

	public void saveStory(Story story) {
		storyRepository.saveStory(story);
	}

	public void completeStory(Story story) {
		story.setStatus(StoryStatus.COMPLETED.getStatus());
		story.setCompletionDate(Calendar.getInstance().getTime());
		storyRepository.updateStory(story);
	}

	public void startStory(Story story) {
		story.setStatus(StoryStatus.STARTED.getStatus());
		story.setStartDate(Calendar.getInstance().getTime());
		storyRepository.updateStory(story);
	}

	public void updateStory(Story story) {
		storyRepository.updateStory(story);
	}

	public void deleteStory(Story story) {
		storyRepository.deleteStory(story);
	}

	public void saveTodoOrder(String projectId, List<String> storyIds) {
		for (int i = 0; i < storyIds.size(); i++) {
			Story story = storyRepository.getStory(storyIds.get(i));
			story.setPriority(i + 1);
			storyRepository.updateStory(story);
		}
	}

}
