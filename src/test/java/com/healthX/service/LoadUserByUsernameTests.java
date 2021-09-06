package com.healthX.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.healthX.dao.UserRepository;
import com.healthX.model.User;

@SpringBootTest
public class LoadUserByUsernameTests {

  @Autowired
  private UserDetailsService userDetailsService;

  @MockBean
  private UserRepository userRepository;

  @Test
  public void loadUserByUsernameWhenUserDoesntExistTest() {
    String username = "username";

    when(userRepository.findUserByUsername(username))
            .thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class,
            () -> userDetailsService.loadUserByUsername(username));
  }

  @Test
  public void loadUserByUsernameWhenUserExistsTest() {
    User user = new User();
    user.setUsername("username");

    when(userRepository.findUserByUsername(user.getUsername()))
            .thenReturn(Optional.of(user));

    UserDetails result = userDetailsService.loadUserByUsername(user.getUsername());

    assertEquals(result.getUsername(), user.getUsername());
  }
}
