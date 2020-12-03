package com.example.demo.controller.restapi;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoRestController.class)
@WithMockUser(value = "spring")
public class TodoRestControllerTests {
  @Autowired
  private MockMvc mockMvc;
  private final String rootUrl = "/v1/todos";

  @MockBean
  private TodoRepository todoRepository;

  @Test
  void success() throws Exception {
    // GIVEN
    when(todoRepository.findList("spring"))
        .thenReturn(List.of(
            TodoDto.builder().id(1).userId("spring").action("醤油").build(),
            TodoDto.builder().id(2).userId("spring").action("豆腐").build()

        ));

    // Language=json
    String expected = "[" +
        "{\n" +
        "  \"id\": 1,\n" +
        "  \"userId\": \"spring\",\n" +
        "  \"action\": \"醤油\"\n" +
        "},\n" +
        "  " +
        "{\n" +
        "    \"id\": 2,\n" +
        "    \"userId\": \"spring\",\n" +
        "    \"action\": \"豆腐\"\n" +
        "  " +
        "}\n" +
        "]";

    this.mockMvc.perform(get(rootUrl + "/spring"))
        .andExpect(status().isOk())
        .andExpect(content().json(expected));
  }

  @Test
  void _404_not_found() throws Exception {
    when(todoRepository.findList("spring"))
        .thenReturn(List.of());

    this.mockMvc.perform(get(rootUrl + "/spring"))
        .andExpect(status().isNotFound())
    ;
  }
}
