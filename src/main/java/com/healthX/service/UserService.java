package com.healthX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthX.dao.UserRepository;
import com.healthX.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addUser(User user) {
		Optional<User> u = userRepository.findUserByUsername(user.getUsername());

		if (u.isEmpty()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} else {
			throw new RuntimeException("User already exists!");
		}
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
