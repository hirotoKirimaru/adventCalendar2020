package com.example.demo.controller;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.controller.security.AuthTargetUser;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class IndexControllerTests {
  private MockMvc mockMvc;

  @Mock
  private TodoRepository todoRepository;
  @Mock
  private HttpSession httpSession;

  @InjectMocks
  private IndexController indexController;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    AuthTargetUser user = new AuthTargetUser(new User("user", "pass", Collections.emptyList()));
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
    SecurityContext securityContext = new SecurityContextImpl(authentication);

    when(httpSession.getAttribute("SPRING_SECURITY_CONTEXT")).thenReturn(securityContext);
  }

  @Test
  void test_01() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        // レスポンスのステータスコードが200であることを検証する
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(model().attribute("todos", Collections.EMPTY_LIST))
    ;
  }

  @Test
  void test_02() throws Exception {
    List<TodoDto> todos = List.of(
        TodoDto.builder().id(1).build(),
        TodoDto.builder().id(2).build(),
        TodoDto.builder().id(3).build()
    );
    when(todoRepository.findList("user")).thenReturn(todos);

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        // レスポンスのステータスコードが200であることを検証する
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(model().attribute("todos", todos))
    ;
  }

  @Nested
  class Add {
    @Test
    void test_01() throws Exception {

      TodoDto expected = TodoDto.builder()
          .id(123)
          .userId("user")
          .action("actionする")
          .build();

      mockMvc.perform(MockMvcRequestBuilders.post("/")
          .param("add", "add")
          .param("id", "123")
          .param("userId", "user")
          .param("action", "actionする")
      )
          .andExpect(status().isFound());

      Mockito.verify(todoRepository).insert(expected);
    }

    @Test
    void test_02() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post("/")
          .param("add", "add")
      )
          .andExpect(status().is4xxClientError());
    }
  }


}

