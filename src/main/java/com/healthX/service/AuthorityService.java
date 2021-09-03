package com.healthX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthX.dao.AuthorityRepository;
import com.healthX.model.Authority;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepository AuthorityRepository;
	
	public void addAuthority(Authority auth) {
		Optional<Authority> a = AuthorityRepository.findAuthorityByName(auth.getName());

		if (a.isEmpty()) {
			AuthorityRepository.save(auth);
		} else {
			throw new RuntimeException("Authority already exists!");
		}
	}
	
	public Authority getAuthorityByName(String name) {
		return AuthorityRepository.findAuthorityByName(name).orElseThrow();
	}

}
