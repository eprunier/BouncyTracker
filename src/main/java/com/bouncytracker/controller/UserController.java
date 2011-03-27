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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bouncytracker.util.ConfigUtil;
import com.bouncytracker.util.view.RequestTarget;

@Controller
public class UserController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConfigUtil.configDateBinder(binder);
	}
	
	@RequestMapping(RequestTarget.REGISTER)
	public void register() {
	}

	@RequestMapping(value={RequestTarget.LOGIN})
	public String login(HttpServletRequest request) {
		return "redirect:/secure/index";
	}
	
	@RequestMapping(RequestTarget.LOGIN_FAILURE)
	public void loginFailure() {
	}
	
}
