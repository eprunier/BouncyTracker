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

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouncytracker.domain.model.Role;
import com.bouncytracker.domain.model.RoleType;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.domain.repository.UserRepository;
import com.bouncytracker.util.PasswordUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User newUser) {
		User user = userRepository.getUser("eric");
		if (user != null) {
			throw new RuntimeException("User '" + user.getEmail() + "' already exists");
		} else {
			digesterPassword(newUser);
			addUserRole(newUser);
			userRepository.saveUser(newUser);
		}
	}
	
	private void digesterPassword(User user) {
		String plainTextPassword = user.getPassword();
		String digestedPassword = PasswordUtil.getInstance().digest(plainTextPassword); 
		user.setPassword(digestedPassword);
	}

	private void addUserRole(User user) {
		Set<Role> roles = new HashSet<Role>();
		Role userRole = new Role();
		userRole.setRole(RoleType.ROLE_USER.name());
		roles.add(userRole);
		user.setRoles(roles);
	}

	@Transactional(readOnly = true)
	public User loadUser(String login) {
		return userRepository.getUser(login);
	}

	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	@Transactional(readOnly = true)
	public User getCurrentUser() {
		String userEmail = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userEmail = ((UserDetails)principal).getUsername();
		} else {
			userEmail = principal.toString();
		}

		return userRepository.getUser(userEmail);
	}
}
