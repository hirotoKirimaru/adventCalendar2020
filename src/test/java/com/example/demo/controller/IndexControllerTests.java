package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.controller.dto.TodoDto;
import com.example.demo.controller.security.AuthTargetUser;
import com.example.demo.controller.security.SecurityConfig;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IndexController.class)
@ContextConfiguration(classes = SecurityConfig.class)
@WebAppConfiguration
class IndexControllerTests {
//  @Autowired
//  WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoRepository todoRepository;
  @Mock
  private HttpSession httpSession;

  @InjectMocks
  private IndexController indexController;
  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders
//        .webAppContextSetup(context)
        .standaloneSetup(indexController)
        .defaultRequest(MockMvcRequestBuilders.get("/").with(user("user").roles("ADMIN")))
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();

//    AuthTargetUser user = new AuthTargetUser(new User("user", "pass", Collections.emptyList()));
//    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
//    SecurityContext securityContext = new SecurityContextImpl(authentication);
//
//    when(httpSession.getAttribute("SPRING_SECURITY_CONTEXT")).thenReturn(securityContext);
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

}

