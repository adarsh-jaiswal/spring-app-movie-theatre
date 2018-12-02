package com.adarsh.udaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="auth")
public class AuthController {
	
	@RequestMapping(value="/signout",method=RequestMethod.GET)
	public ModelAndView displayHomePage() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

}
