package com.nwchat.сontroller;

import com.nwchat.entity.UserEntity;
import com.nwchat.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController  {

	private final UserService userService;

	public IndexController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!auth.isAuthenticated()) {
			return new ModelAndView("redirect:/user/login");
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/home/home");
		return model;
	}

	@RequestMapping(value = "/home/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserEntity user = userService.findUserByLogin(auth.getName());

		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/home");
		return model;
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/404");
		return model;
	}
}
