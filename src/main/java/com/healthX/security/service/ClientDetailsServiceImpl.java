package com.healthX.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.healthX.dao.ClientRepository;
import com.healthX.model.Client;
import com.healthX.security.model.ClientDetailsImpl;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Optional<Client> client = clientRepository.findClientByName(clientId);
		return client.map(ClientDetailsImpl::new)
				.orElseThrow(() -> new ClientRegistrationException("Client not found!"));
	}

}
