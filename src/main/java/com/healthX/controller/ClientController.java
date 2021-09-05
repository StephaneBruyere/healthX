package com.healthX.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthX.model.Client;
import com.healthX.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping
	public void addClient(@RequestBody Client client) {
		clientService.addClient(client);
	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}
}
