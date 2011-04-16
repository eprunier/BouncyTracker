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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bouncytracker.domain.model.User;

@Repository
public final class UserRepository {
	
	@Autowired private SessionFactory sessionFactory;

	public User getUser(String email) {
		return (User)sessionFactory.getCurrentSession().get(User.class, email);
	}

	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	public void saveUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
}
