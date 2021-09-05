package com.healthX.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthX.model.GrantType;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {
	
	Optional<GrantType> findGrantTypeByName(String name);
}
