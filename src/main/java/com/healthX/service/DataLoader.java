package com.healthX.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.healthX.model.Authority;
import com.healthX.model.User;

@Component
public class DataLoader implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	public void run(ApplicationArguments args) {

		Authority auth1 = new Authority();
		auth1.setName("read");
		Authority auth2 = new Authority();
		auth2.setName("write");
		try {
			authorityService.addAuthority(auth1);
			authorityService.addAuthority(auth2);
		} catch (Exception e) {
			LOGGER.debug("Authority table already initialized");
		}
		
		User user1 = new User();
		user1.setUsername("Bob");
		user1.setPassword("pwd1");
		user1.setAuthorities(List.of(authorityService.getAuthorityByName("read")));

		User user2 = new User();
		user2.setUsername("Bill");
		user2.setPassword("pwd2");
		user2.setAuthorities(List.of(authorityService.getAuthorityByName("write")));

		try {
			userService.addUser(user1);
			userService.addUser(user2);
		} catch (Exception e) {
			LOGGER.debug("User table already initialized");
		}
	}
}
