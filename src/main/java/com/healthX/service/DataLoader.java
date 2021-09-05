package com.healthX.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.healthX.model.Authority;
import com.healthX.model.Client;
import com.healthX.model.GrantType;
import com.healthX.model.User;

@Component
public class DataLoader implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private GrantTypeService grantTypeService;

	public void run(ApplicationArguments args) {
		GrantType grantType1 = new GrantType();
		grantType1.setName("authorization_code");
		GrantType grantType2 = new GrantType();
		grantType2.setName("password");
		GrantType grantType3 = new GrantType();
		grantType3.setName("refresh_token");
		try {
			grantTypeService.addGrantType(grantType1);
			grantTypeService.addGrantType(grantType2);
			grantTypeService.addGrantType(grantType3);
		} catch (Exception e) {
			LOGGER.debug("GrantType table already initialized");
		}
		
		Client client1 = new Client();
		client1.setName("client1");
		client1.setSecret("secret1");
		client1.setRedirectURI("http://localhost:8080");
		client1.setScope("read");
		client1.setGrantTypes(List.of(grantType1, grantType2, grantType3));
		try {
			clientService.addClient(client1);
		} catch (Exception e) {
			LOGGER.debug("Client table already initialized");
		}

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
