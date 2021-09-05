package com.healthX.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthX.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	Optional<Client> findClientByName(String name);

}
