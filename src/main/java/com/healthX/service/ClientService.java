package com.healthX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthX.dao.ClientRepository;
import com.healthX.model.Client;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addClient(Client client) {
		Optional<Client> c = clientRepository.findClientByName(client.getName());

		if (c.isEmpty()) {
			client.setSecret(passwordEncoder.encode(client.getSecret()));
			clientRepository.save(client);
		} else {
			throw new RuntimeException("Client already exists!");
		}
	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

}
