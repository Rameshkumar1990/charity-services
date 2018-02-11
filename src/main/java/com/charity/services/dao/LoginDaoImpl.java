package com.charity.services.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class LoginDaoImpl extends NamedParameterJdbcDaoSupport implements LoginDao {

	public String getPassword(String username) {
		String sql = "select password from login where username=:username";

		SqlParameterSource paramSource = new MapSqlParameterSource("username", username);

		String password = getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, String.class);

		return password;
	}
}
