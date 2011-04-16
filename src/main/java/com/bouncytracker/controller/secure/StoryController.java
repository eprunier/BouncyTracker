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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouncytracker.controller.AbstractController;
import com.bouncytracker.controller.secure.form.StoryForm;
import com.bouncytracker.domain.model.Story;
import com.bouncytracker.service.ProjectService;
import com.bouncytracker.util.ConfigHelper;
import com.bouncytracker.view.ModelConstants;
import com.bouncytracker.view.RequestConstants;
import com.bouncytracker.view.RequestTarget;

@Controller
public final class StoryController extends AbstractController {

	@Autowired private ProjectService projectService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConfigHelper.configDateBinder(binder);
	}
	
	@RequestMapping(RequestTarget.STORY_CREATE + "/" + RequestConstants.PROJECT_ID)
	public String create(@PathVariable String projectId, ModelMap model) {
		Story story = new Story();
		story.setProject(projectService.loadProject(projectId));

		StoryForm form = new StoryForm();
		form.loadFromStory(story);
		
		model.addAttribute(ModelConstants.STORY, form);
		return RequestTarget.STORY_CREATE;
	}
	
	@RequestMapping(
			value=RequestTarget.STORY_CREATE,
			method={RequestMethod.POST}
	)
	public String create(@ModelAttribute(ModelConstants.STORY) @Valid StoryForm form, BindingResult result) {
		if (result.hasErrors()) {
			return RequestTarget.STORY_CREATE;
		}
		
		Story story = form.asStory();
		projectService.createStory(story);
		return "redirect:" + RequestTarget.PROJECT_SHOW + "/" + story.getProject().getId();
	}
	
	@RequestMapping(RequestTarget.STORY_COMPLETE + "/" + RequestConstants.ID)
	public String complete(@PathVariable String id) {
		Story story = projectService.loadStoryWithProject(id);
		projectService.completeStory(story);
		return redirect(RequestTarget.PROJECT_SHOW + "/" + story.getProject().getId());

	}
	
	@RequestMapping(RequestTarget.STORY_START + "/" + RequestConstants.ID)
	public String start(@PathVariable String id) {
		Story story = projectService.loadStoryWithProject(id);
		projectService.startStory(story);
		return redirect(RequestTarget.PROJECT_SHOW + "/" + story.getProject().getId());
	}
	
	@RequestMapping(
			value=RequestTarget.STORY_UPDATE + "/" + RequestConstants.ID,
			method=RequestMethod.GET
	)
	public String update(@PathVariable String id, ModelMap model) {
		StoryForm data = new StoryForm();
		data.loadFromStory(projectService.loadStoryWithProject(id));
		model.addAttribute("story", data);
		return RequestTarget.STORY_UPDATE;
	}
	
	@RequestMapping(
			value=RequestTarget.STORY_UPDATE,
			method={RequestMethod.POST}
	)
	public String update(@ModelAttribute(ModelConstants.STORY) @Valid StoryForm data, BindingResult result) {
		if (result.hasErrors()) {
			return RequestTarget.STORY_UPDATE;
		}

		Story story = data.asStory();
		projectService.updateStory(story);
		
		return redirect (RequestTarget.PROJECT_SHOW + "/" + story.getProject().getId());
	}
	
	@RequestMapping(RequestTarget.STORY_DELETE + "/" + RequestConstants.ID)
	public String delete(@PathVariable String id) {
		Story story = projectService.loadStoryWithProject(id);
		projectService.deleteStory(story);
		return redirect(RequestTarget.PROJECT_SHOW + "/" + story.getProject().getId());
	}
}
