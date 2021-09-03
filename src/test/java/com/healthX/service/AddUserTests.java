package com.healthX.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healthX.dao.UserRepository;
import com.healthX.model.Authority;
import com.healthX.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddUserTests {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void addUserWhenUserDoesntExistTest() {
    User user = new User();
    user.setUsername("username");
    user.setPassword("password");
    user.setAuthorities(List.of(new Authority()));

    when(userRepository.findUserByUsername(user.getUsername()))
            .thenReturn(Optional.empty());

    userService.addUser(user);

    verify(passwordEncoder).encode("password");
    verify(userRepository).save(user);
  }

  @Test
  public void addUserWhenUserExistsTest() {
    User user = new User();
    user.setUsername("username");
    user.setPassword("password");
    user.setAuthorities(List.of(new Authority()));

    when(userRepository.findUserByUsername(user.getUsername()))
            .thenReturn(Optional.of(user));

    assertThrows(RuntimeException.class,
            () -> userService.addUser(user));

    verify(passwordEncoder, never()).encode("password");
    verify(userRepository, never()).save(user);
  }
}
