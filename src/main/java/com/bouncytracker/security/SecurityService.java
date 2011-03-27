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

package com.bouncytracker.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouncytracker.domain.model.Role;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.UserService;

@Service("userDetailsService")
@Transactional
public class SecurityService implements UserDetailsService {

	@Autowired
	private UserService userManager;
	
	public UserDetails loadUserByUsername(String login)
	throws UsernameNotFoundException, DataAccessException {
		User user = userManager.loadUser(login);
		if (user == null) {
			throw new UsernameNotFoundException("Unknown user '" + login + "'");
		}
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			auths.add(new GrantedAuthorityImpl(role.getRole()));
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), 
				user.getPassword(), 
				user.isEnabled(), 
				true, 
				true, 
				true, 
				auths
		);
	}

}
