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

package com.bouncytracker.view;

public interface RequestTarget {

	String REDIRECT = "redirect:";
	
	String SLASH = "/";
	String INDEX = "/index";
	
	String REGISTER = "/register";
	String LOGIN = "/login";
	String LOGIN_FAILURE = "/loginFailure";
	
	String SECURE_SLASH = "/secure/";
	String SECURE_INDEX = "/secure/index";
	
	String PROJECT_SHOW = "/secure/project/show";
	String PROJECT_CREATE = "/secure/project/create";
	String PROJECT_UPDATE = "/secure/project/update";
	String PROJECT_DELETE = "/secure/project/delete";
	String PROJECT_AJAX_SORT = "/secure/project/sort/ajax";

	String STORY_CREATE = "/secure/story/create";
	String STORY_UPDATE = "/secure/story/update";
	String STORY_DELETE = "/secure/story/delete";
	String STORY_START = "/secure/story/start";
	String STORY_COMPLETE = "/secure/story/complete";

	String TASK_SLASH = "/secure/task/";
	String TASK_INDEX = "/secure/task/index";
	String TASK_CREATE = "/secure/task/create";
	String TASK_UPDATE = "/secure/task/update";
	String TASK_COMPLETE = "/secure/task/complete";
	String TASK_REACTIVATE = "/secure/task/reactivate";
	String TASK_DELETE = "/secure/task/delete";

	String USER_ACCOUNT_SLASH = "/secure/account/";
	String USER_ACCOUNT_INDEX = "/secure/account/index";
	String USER_ACCOUNT_UPDATE = "/secure/account/update";
}
