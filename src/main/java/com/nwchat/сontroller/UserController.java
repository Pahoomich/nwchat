package com.nwchat.сontroller;

import com.nwchat.entity.UserEntity;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/login");
		return model;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		UserEntity user = new UserEntity();
		model.addObject("user", user);
		model.setViewName("user/signup");

		return model;
	}

	@RequestMapping(value= "/signup", method=RequestMethod.POST)
	public ModelAndView createUser(@Valid UserEntity user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		UserEntity userExists = userService.findUserByLogin(user.getLogin());

		if(userExists != null) {
			bindingResult.rejectValue("login", "error.user", "This login already exists!");
		}
		if(bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		} else {
			user.setRoleId(1);
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.setViewName("redirect:/user/login");
		}
		return model;
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkLogin(@RequestBody String login) {
		UserEntity userExists = userService.findUserByLogin(login);

		return new ResponseEntity<>(userExists != null, HttpStatus.OK);
	}


}
