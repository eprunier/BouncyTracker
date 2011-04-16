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

package com.bouncytracker.controller.secure.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bouncytracker.domain.model.Project;
import com.bouncytracker.domain.model.Story;

public final class StoryForm {

	@NotNull @NotEmpty private String label;
	@NotNull @NotEmpty private String description;
	@NotNull private int priority;
	@NotNull private int category;
	@NotNull private Project project;	
	private String id;
	private int status;
	private Date startDate;
	private Date completionDate;
	
	public void loadFromStory(Story story) {
		id = story.getId();
		project = story.getProject();
		label = story.getLabel();
		description = story.getDescription();
		priority = story.getPriority();
		status = story.getStatus();
		startDate = story.getStartDate();
		completionDate = story.getCompletionDate();
		category = story.getCategory();
	}
	
	public Story asStory() {
		Story story = new Story();
		story.setId(id);
		story.setProject(project);
		story.setLabel(label);
		story.setDescription(description);
		story.setPriority(priority);
		story.setStatus(status);
		story.setStartDate(startDate);
		story.setCompletionDate(completionDate);
		story.setCategory(category);
		return story;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String details) {
		this.description = details;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
