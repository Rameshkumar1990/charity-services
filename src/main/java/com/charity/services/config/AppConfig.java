package com.charity.services.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.charity.services.dao.LoginDao;
import com.charity.services.dao.LoginDaoImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.charity.services" })
@PropertySources({ @PropertySource("classpath:db-config.properties") })
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.connection.driver"));
		dataSource.setUrl(env.getProperty("mysql.connection.url"));
		dataSource.setUsername(env.getProperty("mysql.connection.username"));
		dataSource.setPassword(env.getProperty("mysql.connection.password"));
		return dataSource;
	}

	@Bean
	public LoginDao loginDaoImpl() {
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
		loginDaoImpl.setDataSource(dataSource());
		return loginDaoImpl;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("");
		mailSender.setPassword("");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}
}
