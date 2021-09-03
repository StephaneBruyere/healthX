package com.healthX.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthX.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByUsername(String username);

}
