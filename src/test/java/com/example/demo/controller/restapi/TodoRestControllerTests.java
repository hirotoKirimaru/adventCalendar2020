package com.example.demo.controller.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoRestController.class)
public class TodoRestControllerTests {
  @Autowired
  private MockMvc mockMvc;
  private final String rootUrl = "/v1/todos";

  @WithMockUser(value = "spring")
  @Test
  void success() throws Exception {
    this.mockMvc.perform(get(rootUrl))
        .andExpect(status().isOk())
        .andExpect(content().string("hogehoge"));
  }
}
