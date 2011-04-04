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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;

import com.bouncytracker.domain.model.User;

public class RegistrationFormData extends UserFormData {

	public void loadFromUser(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
	
	public User asUser(BindingResult result) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		return user;
	}
	
	public void setPassword(@NotNull @NotEmpty String password) {
		this.password = password;
	}
	
	public void setVerifyPassword(@NotNull @NotEmpty String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

}
