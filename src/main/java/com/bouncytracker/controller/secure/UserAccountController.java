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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouncytracker.controller.secure.formdata.UserFormData;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.UserManager;
import com.bouncytracker.util.ConfigUtil;
import com.bouncytracker.util.Message;
import com.bouncytracker.util.view.RequestTarget;

@Controller
public class UserAccountController {

	@Autowired private UserManager userManager;

	@ModelAttribute("user")
	public UserFormData initForm(HttpServletRequest request) {
		User user = userManager.getCurrentUser();
		UserFormData data = new UserFormData();
		data.setEmail(user.getEmail());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
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
	public String submitForm(@ModelAttribute("user") @Valid UserFormData data, BindingResult result) {
		User user = userManager.loadUser(data.getEmail());
		
		user.setEmail(data.getEmail());
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		
		if (!"".equals(data.getPassword().trim())) {
			if (data.getPassword().equals(data.getVerifyPassword())) {
				user.setPassword(data.getPassword());
			} else {
				FieldError error = new FieldError(
						"user", 
						"verifyPassword", 
						ConfigUtil.getMessage(Message.ERROR_PASSWORD_VERIFY.getKey())
				);
				result.addError(error);
			}
		}

		if (result.hasErrors()) {
			return RequestTarget.USER_ACCOUNT_UPDATE;
		}
		
		userManager.updateUser(user);
		return RequestTarget.USER_ACCOUNT_INDEX;
	}
}
