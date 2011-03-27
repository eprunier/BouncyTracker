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

package com.bouncytracker.domain.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bouncytracker.domain.model.Project;
import com.bouncytracker.domain.model.User;

@Repository
@SuppressWarnings("unchecked")
public class ProjectRepository {

	@Autowired private SessionFactory sessionFactory;

	public Project getProject(String id) {
		return (Project)sessionFactory.getCurrentSession().get(Project.class, id);
	}
	
	public Project findProjectByName(String name) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Project.findByName");
		query.setParameter("name", name);
		return (Project)query.uniqueResult();
	}
	
	public void saveProject(Project project) {
		sessionFactory.getCurrentSession().save(project);		
	}
	
	public List<Project> listProjectsWithStartedStoriesForUser(User user) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Project.listUserProjects");
		query.setParameter("user", user);
		return query.list();
	}

	public Project loadProjectWithStories(String id) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Project.loadProjectWithStories");
		query.setParameter("id", id);
		return (Project)query.uniqueResult();
	}

	public void updateProject(Project project) {
		sessionFactory.getCurrentSession().update(project);
	}

	public void deleteProject(Project project) {
		sessionFactory.getCurrentSession().delete(project);
	}

}
