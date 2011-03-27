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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.bouncytracker.domain.model.User;
import com.bouncytracker.util.ConfigUtil;
import com.bouncytracker.util.Message;
import com.bouncytracker.util.PasswordUtil;

public class UserFormData {

	@NotNull @NotEmpty @Email protected String email;
	
	protected String password;
	protected String verifyPassword;
	
	@NotNull @NotEmpty protected String firstName;
	@NotNull @NotEmpty protected String lastName;

	public void loadFromUser(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
	
	public void updateUser(User user, BindingResult result) {
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		updatePassword(user, result);
	}
	
	protected void updatePassword(User user, BindingResult result) {
		if (!"".equals(password.trim())) {
			if (password.equals(verifyPassword)) {
				user.setPassword(PasswordUtil.getInstance().digest(password));
			} else {
				FieldError error = new FieldError(
						"user", 
						"verifyPassword", 
						ConfigUtil.getMessage(Message.ERROR_PASSWORD_VERIFY.getKey())
				);
				result.addError(error);
			}
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
