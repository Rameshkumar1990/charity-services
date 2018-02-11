package com.charity.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charity.services.model.User;
import com.charity.services.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginServiceImpl;

	@RequestMapping(value = "/charity/services/v1/login", method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		return loginServiceImpl.login(user) ? "Login Success" : "Login Failure";
	}

	@RequestMapping(value = "/charity/services/v1/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}
}
