package com.charity.services.service.impl;

import com.charity.services.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charity.services.dao.LoginDao;
import com.charity.services.model.User;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginDao loginDaoImpl;

	public boolean login(User user) {
		boolean validUser = false;
		String actualPassword = loginDaoImpl.getPassword(user.getUsername());
		if (StringUtils.isNotBlank(user.getPassword()) && user.getPassword().equals(actualPassword)) {
			validUser = true;
		}
		LOGGER.info("User login validated ");
		return validUser;
	}
}
