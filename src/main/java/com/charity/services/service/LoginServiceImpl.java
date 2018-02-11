package com.charity.services.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charity.services.dao.LoginDao;
import com.charity.services.model.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDaoImpl;

	public boolean login(User user) {
		boolean validUser = false;
		String actualPassword = loginDaoImpl.getPassword(user.getUsername());
		if (StringUtils.isNotBlank(user.getPassword()) && user.getPassword().equals(actualPassword)) {
			validUser = true;
		}
		return validUser;
	}
}
