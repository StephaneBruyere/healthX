package com.healthX.token;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TokenTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void generateTokenValidUserAndClientTest() throws Exception {

    mvc.perform(
            post("/oauth/token")
                    .with(httpBasic("client", "secret"))
                    .queryParam("grant_type", "password")
                    .queryParam("username", "stef")
                    .queryParam("password", "pwd")
                    .queryParam("scope", "read")
    )
            .andExpect(jsonPath("$.access_token").exists())
            .andExpect(jsonPath("$.refresh_token").exists())
            .andExpect(jsonPath("$.expires_in").exists())
            .andExpect(jsonPath("$.token_type").value("bearer"))
            .andExpect(jsonPath("$.scope").value("read"))
            .andExpect(status().isOk());

  }

  @Test
  void generateTokenInvalidClientTest() throws Exception {

    mvc.perform(
            post("/oauth/token")
                    .with(httpBasic("other_client", "secret"))
                    .queryParam("grant_type", "password")
                    .queryParam("username", "stef")
                    .queryParam("password", "pwd")
                    .queryParam("scope", "read")
    )
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.access_token").doesNotExist());

  }

  @Test
  void generateTokenInvalidUserTest() throws Exception {

    mvc.perform(
            post("/oauth/token")
                    .with(httpBasic("client", "secret"))
                    .queryParam("grant_type", "password")
                    .queryParam("username", "other_user")
                    .queryParam("password", "password")
                    .queryParam("scope", "read")
    )
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.access_token").doesNotExist());

  }

  @Test
  void generateTokenPasswordNotValidTest() throws Exception {

    mvc.perform(
            post("/oauth/token")
                    .with(httpBasic("client", "other_secret"))
                    .queryParam("grant_type", "password")
                    .queryParam("username", "user")
                    .queryParam("password", "other_password")
                    .queryParam("scope", "read")
    )
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.access_token").doesNotExist());

  }

}
