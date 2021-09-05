package com.healthX.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healthX.dao.ClientRepository;
import com.healthX.model.Client;
import com.healthX.model.GrantType;

@SpringBootTest
public class AddClientTests {

  @Autowired
  private ClientService clientService;

  @MockBean
  private ClientRepository clientRepository;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void addClientWhenClientDoesntExistTest() {
    Client c = new Client();
    c.setName("client");
    c.setSecret("secret");
    c.setGrantTypes(List.of(new GrantType()));

    when(clientRepository.findClientByName(c.getName()))
            .thenReturn(Optional.empty());

    clientService.addClient(c);

    verify(passwordEncoder).encode("secret");
    verify(clientRepository).save(c);
  }

  @Test
  public void addClientWhenClientAlreadyExistsTest() {
    Client c = new Client();
    c.setName("client");
    c.setSecret("secret");

    when(clientRepository.findClientByName(c.getName()))
            .thenReturn(Optional.of(c));

    assertThrows(RuntimeException.class
            , () -> clientService.addClient(c));

    verify(passwordEncoder, never()).encode(c.getSecret());
    verify(clientRepository, never()).save(c);
  }
}
