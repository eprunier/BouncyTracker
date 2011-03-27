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

package com.bouncytracker.controller.secure.formdata;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bouncytracker.domain.model.Project;
import com.bouncytracker.domain.model.User;

public class ProjectFormData {

	@NotNull @NotEmpty private String name;
	@NotNull private User user;

	private String id;
	private String description;
	private Date startDate;
	private int iterationLength;
	
	public void loadFromProject(Project project) {
		id = project.getId();
		user = project.getUser();
		name = project.getName();
		description = project.getDescription();
		startDate = project.getStartDate();
		iterationLength = project.getIterationLength();
	}
	
	public Project asProject() {
		Project project = new Project();
		project.setId(id);
		project.setUser(user);
		project.setName(name);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setIterationLength(iterationLength);
		return project;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getIterationLength() {
		return iterationLength;
	}

	public void setIterationLength(int iterationLength) {
		this.iterationLength = iterationLength;
	}

}
