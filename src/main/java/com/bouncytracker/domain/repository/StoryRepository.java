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

import com.bouncytracker.domain.model.Story;

@Repository
@SuppressWarnings("unchecked")
public class StoryRepository {

	@Autowired private SessionFactory sessionFactory;

	public List<Story> findStoriesForProject(String projectId) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Story.findByProjectId");
		query.setParameter("projectId", projectId);
		return query.list();
	}

	public void saveStory(Story story) {
		sessionFactory.getCurrentSession().save(story);
	}
	
	public void updateStory(Story story) {
		sessionFactory.getCurrentSession().update(story);
	}

	public Story getStory(String id) {
		return (Story)sessionFactory.getCurrentSession().get(Story.class, id);
	}

	public Story getStoryWithProject(String id) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Story.loadStoryWithProject");
		query.setParameter("id", id);
		return (Story)query.uniqueResult();
	}

	public void deleteStory(Story story) {
		sessionFactory.getCurrentSession().delete(story);
	}

}
