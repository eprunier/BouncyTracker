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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouncytracker.controller.secure.formdata.RegistrationFormData;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.UserService;
import com.bouncytracker.util.ConfigUtil;
import com.bouncytracker.util.view.RequestTarget;

@Controller
public class UserController {
	
	@Autowired private UserService userManager;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConfigUtil.configDateBinder(binder);
	}
	
	@RequestMapping(
			value=RequestTarget.REGISTER,
			method=RequestMethod.GET
	)
	public void register(ModelMap model) {
		User user = new User();
		
		RegistrationFormData data = new RegistrationFormData();
		data.loadFromUser(user);
		
		model.addAttribute("user", data);
	}

	@RequestMapping(
			value=RequestTarget.REGISTER,
			method=RequestMethod.POST
	)
	public String register(@ModelAttribute("user") @Valid RegistrationFormData data, BindingResult result) {
		User user = data.asUser(result);
		
		if (result.hasErrors()) {
			return RequestTarget.REGISTER;
		}

		userManager.createUser(user);
		return "redirect:" + RequestTarget.SECURE_INDEX;
	}

	@RequestMapping(value={RequestTarget.LOGIN})
	public String login(HttpServletRequest request) {
		return "redirect:" + RequestTarget.SECURE_INDEX;
	}
	
	@RequestMapping(RequestTarget.LOGIN_FAILURE)
	public void loginFailure() {
	}
	
}
