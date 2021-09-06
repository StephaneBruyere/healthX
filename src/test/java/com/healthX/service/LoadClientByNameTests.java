package com.healthX.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.healthX.dao.ClientRepository;
import com.healthX.model.Client;

@SpringBootTest
public class LoadClientByNameTests {

  @Autowired
  private ClientDetailsService clientDetailsService;

  @MockBean
  private ClientRepository clientRepository;

  @Test
  public void loadClientByClientIdClientDoesntExistTest() {
    String clientId = "client";

    when(clientRepository.findClientByName(clientId))
            .thenReturn(Optional.empty());

    assertThrows(ClientRegistrationException.class,
            () -> clientDetailsService.loadClientByClientId(clientId));
  }

  @Test
  public void loadClientByClientIdTest() {
    Client c = new Client();
    c.setName("client");

    when(clientRepository.findClientByName(c.getName()))
            .thenReturn(Optional.of(c));

    ClientDetails result = clientDetailsService.loadClientByClientId(c.getName());

    assertEquals(result.getClientId(), c.getName());
  }
}
