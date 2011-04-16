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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bouncytracker.controller.AbstractController;
import com.bouncytracker.controller.secure.form.ProjectForm;
import com.bouncytracker.domain.model.Project;
import com.bouncytracker.domain.model.User;
import com.bouncytracker.service.ProjectService;
import com.bouncytracker.service.StoryDispatcher;
import com.bouncytracker.service.UserService;
import com.bouncytracker.util.ConfigHelper;
import com.bouncytracker.view.ModelConstants;
import com.bouncytracker.view.RequestTarget;
import com.bouncytracker.view.ViewFields;

@Controller
public final class ProjectController extends AbstractController {

	@Autowired private UserService userService;
	@Autowired private ProjectService projectService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConfigHelper.configDateBinder(binder);
	}
	
	@RequestMapping(value={RequestTarget.SECURE_SLASH, RequestTarget.SECURE_INDEX})
	public ModelAndView secure() {
		User user = userService.getCurrentUser();
		List<Project> projects = projectService.listUserProjectsWithStartedStories(user);
	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(ModelConstants.USER, user);
		model.put("projects", projects);
		return new ModelAndView(RequestTarget.SECURE_INDEX, model);
	}	
	
	@RequestMapping(RequestTarget.PROJECT_SHOW + "/{id}")
	public ModelAndView show(@PathVariable String id) {
		Project project = projectService.loadProjectWithStories(id);
		
		StoryDispatcher storyDispatcher = new StoryDispatcher();
		storyDispatcher.dispatch(project.getStories());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(ModelConstants.PROJECT, project);
		model.put(ModelConstants.TODO, storyDispatcher.getTodo());
		model.put("currentStories", storyDispatcher.getCurrentStories());
		model.put("completedStories", storyDispatcher.getCompletedStories());
		return new ModelAndView(RequestTarget.PROJECT_SHOW, model);
	}
	
	@RequestMapping(
			value=RequestTarget.PROJECT_CREATE,
			method=RequestMethod.GET
	)
	public void create(ModelMap model) {
		Project project = new Project();
		project.setUser(userService.getCurrentUser());
		
		ProjectForm form = new ProjectForm();
		form.loadFromProject(project);
		
		model.addAttribute(ModelConstants.PROJECT, form);
	}
	
	@RequestMapping(
			value=RequestTarget.PROJECT_CREATE,
			method=RequestMethod.POST
	)
	public String create(@ModelAttribute("project") @Valid ProjectForm data, BindingResult result) {
		if (result.hasErrors()) {
			return RequestTarget.PROJECT_CREATE;
		}
		
		Project project = data.asProject();
		projectService.createProject(project);
		return redirect(RequestTarget.SECURE_INDEX);
	}
	
	@RequestMapping(
			value=RequestTarget.PROJECT_UPDATE + "/{id}",
			method=RequestMethod.GET
	)
	public String update(@PathVariable String id, ModelMap model) {
		ProjectForm form = new ProjectForm();
		form.loadFromProject(projectService.loadProject(id));
		model.addAttribute(ModelConstants.PROJECT, form);
		return RequestTarget.PROJECT_UPDATE;
	}

	@RequestMapping(
			value=RequestTarget.PROJECT_UPDATE,
			method=RequestMethod.POST
	)
	public String update(@ModelAttribute("project") @Valid ProjectForm data, BindingResult result) {
		if (result.hasErrors()) {
			return RequestTarget.PROJECT_UPDATE;
		}
		
		Project project = data.asProject();
		projectService.updateProject(project);
		return redirect(RequestTarget.PROJECT_SHOW + "/" + project.getId());
	}

	@RequestMapping(RequestTarget.PROJECT_DELETE + "/{id}")
	public String delete(@PathVariable String id) {
		projectService.deleteProject(id);
		return redirect(RequestTarget.SECURE_INDEX);
	}
	
	@RequestMapping(
			value=RequestTarget.PROJECT_AJAX_SORT,
			method=RequestMethod.POST
	)
	@ResponseBody 
	public void sortTodo(@RequestBody String requestBody) {
		String projectId = "";
		List<String> storyIds = new ArrayList<String>();
		
		String[] params = getRequestParams(requestBody);
		for (String param : params) {
			String[] keyValue = getKeyValue(param);
			String key = getKey(keyValue);
			String value = getValue(keyValue);
			
			if (isProjectIdKey(key)) {
				projectId = value;
			} else {
				storyIds.add(value);
			}
		}
		
		projectService.saveTodoOrder(projectId, storyIds);
	}
	
	private String[] getRequestParams(String request) {
		return request.split("&");
	}
	
	private String[] getKeyValue(String item) {
		return item.split("=");
	}
	
	private String getKey(String[] keyValue) {
		return keyValue[0];
	}
	
	private String getValue(String[] keyValue) {
		return keyValue[1];
	}
	
	private boolean isProjectIdKey(String key) {
		return ViewFields.PROJECT_ID.getFieldName().equals(key);
	}
}
