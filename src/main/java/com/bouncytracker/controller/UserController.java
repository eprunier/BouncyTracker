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

package com.bouncytracker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouncytracker.controller.secure.form.UserForm;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.UserService;
import com.bouncytracker.util.ConfigHelper;
import com.bouncytracker.util.Message;
import com.bouncytracker.util.TypesHelper;
import com.bouncytracker.view.RequestTarget;

@Controller
public final class UserController {

	@Autowired private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConfigHelper.configDateBinder(binder);
	}

	@RequestMapping(
			value=RequestTarget.REGISTER,
			method=RequestMethod.GET
	)
	public void register(ModelMap model) {
		User user = new User();

		UserForm form = new UserForm();
		form.loadFromUser(user);

		model.addAttribute("user", form);
	}

	@RequestMapping(
			value=RequestTarget.REGISTER,
			method=RequestMethod.POST
	)
	public String register(@ModelAttribute("user") @Valid UserForm form, BindingResult result) {
		checkPassword(form, result);

		if (result.hasErrors()) {
			return RequestTarget.REGISTER;
		}

		User user = form.asUser(result);
		userService.createUser(user);
		return "redirect:" + RequestTarget.INDEX;
	}
	
	private void checkPassword(UserForm form, BindingResult result) {
		if (TypesHelper.isBlank(form.getPassword()) || TypesHelper.isBlank(form.getVerifyPassword())) {
			result.addError(
					new FieldError(
							"user", 
							"password", 
							ConfigHelper.getMessage(Message.ERROR_EMPTY_PASSWORD.getKey())
					)
			);
		}
	}

	@RequestMapping(value={RequestTarget.LOGIN})
	public String login(HttpServletRequest request) {
		return "redirect:" + RequestTarget.SECURE_INDEX;
	}

	@RequestMapping(RequestTarget.LOGIN_FAILURE)
	public void loginFailure() {
	}

}
