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

package com.bouncytracker.controller.secure;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouncytracker.controller.secure.form.UserForm;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.UserService;
import com.bouncytracker.view.RequestTarget;

@Controller
public final class UserAccountController {

	@Autowired private UserService userService;

	@ModelAttribute("user")
	public UserForm initForm(HttpServletRequest request) {
		User user = userService.getCurrentUser();
		UserForm data = new UserForm();
		data.loadFromUser(user);
		return data;
	}

	@RequestMapping(
			value={RequestTarget.USER_ACCOUNT_SLASH, RequestTarget.USER_ACCOUNT_INDEX},
			method=RequestMethod.GET
	)
	public String show() {
		return RequestTarget.USER_ACCOUNT_INDEX;
	}

	@RequestMapping(
			value=RequestTarget.USER_ACCOUNT_UPDATE,
			method=RequestMethod.GET
	)
	public void udpate() {
	}

	@RequestMapping(
			value=RequestTarget.USER_ACCOUNT_UPDATE, 
			method=RequestMethod.POST
	)
	public String update(@ModelAttribute("user") @Valid UserForm data, BindingResult result) {
		User user = userService.loadUser(data.getEmail());
		data.updateUser(user, result);
		
		if (result.hasErrors()) {
			return RequestTarget.USER_ACCOUNT_UPDATE;
		}
		
		userService.updateUser(user);
		return RequestTarget.USER_ACCOUNT_INDEX;
	}
}
