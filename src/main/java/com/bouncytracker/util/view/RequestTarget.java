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

package com.bouncytracker.util.view;

public interface RequestTarget {

	static final String SLASH = "/";
	static final String INDEX = "/index";
	
	static final String REGISTER = "/register";
	static final String LOGIN = "/login";
	static final String LOGIN_FAILURE = "/loginFailure";
	
	static final String SECURE_SLASH = "/secure/";
	static final String SECURE_INDEX = "/secure/index";
	
	static final String PROJECT_SHOW = "/secure/project/show";
	static final String PROJECT_CREATE = "/secure/project/create";
	static final String PROJECT_UPDATE = "/secure/project/update";
	static final String PROJECT_DELETE = "/secure/project/delete";
	static final String PROJECT_AJAX_SORT = "/secure/project/sort/ajax";

	static final String STORY_CREATE = "/secure/story/create";
	static final String STORY_UPDATE = "/secure/story/update";
	static final String STORY_DELETE = "/secure/story/delete";
	static final String STORY_START = "/secure/story/start";
	static final String STORY_COMPLETE = "/secure/story/complete";

	static final String TASK_SLASH = "/secure/task/";
	static final String TASK_INDEX = "/secure/task/index";
	static final String TASK_CREATE = "/secure/task/create";
	static final String TASK_UPDATE = "/secure/task/update";
	static final String TASK_COMPLETE = "/secure/task/complete";
	static final String TASK_REACTIVATE = "/secure/task/reactivate";
	static final String TASK_DELETE = "/secure/task/delete";

	static final String USER_ACCOUNT_SLASH = "/secure/account/";
	static final String USER_ACCOUNT_INDEX = "/secure/account/index";
	static final String USER_ACCOUNT_UPDATE = "/secure/account/update";
}
