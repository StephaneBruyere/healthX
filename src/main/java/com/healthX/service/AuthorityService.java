package com.healthX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthX.dao.AuthorityRepository;
import com.healthX.model.Authority;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	public void addAuthority(Authority auth) {
		Optional<Authority> a = authorityRepository.findAuthorityByName(auth.getName());

		if (a.isEmpty()) {
			authorityRepository.save(auth);
		} else {
			throw new RuntimeException("Authority already exists!");
		}
	}
	
	public Authority getAuthorityByName(String name) {
		return authorityRepository.findAuthorityByName(name).orElseThrow();
	}

}
