package com.healthX.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthX.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	Optional<Authority> findAuthorityByName(String name);
}
