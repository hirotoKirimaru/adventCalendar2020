package com.example.demo.repository;

import com.example.demo.controller.dto.TodoDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
public class TodoRepositoryTests {
  @Autowired
  TodoRepository todoRepository;

  @Nested
  class Insert {
    @DisplayName("insertした結果をfindする")
    @Test
    public void test_01() {
      TodoDto todoDto = TodoDto.builder().id(1).userId("kirimaru").action("豆腐買う").build();

      todoRepository.insert(todoDto);

      final List<TodoDto> kirimaru = todoRepository.findList("kirimaru");
      assertThat(kirimaru).isEqualTo(
          List.of(todoDto)
      );
    }

    @DisplayName("insertした結果をfindする。複数。")
    @Test
    public void test_02() {
      TodoDto todoDto = TodoDto.builder().id(1).userId("kirimaru").action("豆腐買う").build();
      TodoDto todoDto2 = TodoDto.builder().id(2).userId("kirimaru").action("郵便局行く").build();

      todoRepository.insert(todoDto);
      todoRepository.insert(todoDto2);

      final List<TodoDto> kirimaru = todoRepository.findList("kirimaru");
      assertThat(kirimaru).isEqualTo(
          List.of(todoDto, todoDto2)
      );
    }
  }

  @Nested
  class Update {
    @DisplayName("updateを行う")
    @Test
    public void test_01() {
      // GIVEN
      TodoDto todoDto = TodoDto.builder().id(1).userId("kirimaru").action("豆腐買う").build();
      todoRepository.insert(todoDto);

      // WHEN
      TodoDto expected = TodoDto.builder().id(1).userId("kirimaru").action("納豆買う").build();
      todoRepository.update(expected);

      final List<TodoDto> kirimaru = todoRepository.findList("kirimaru");
      assertThat(kirimaru).isEqualTo(
          List.of(expected)
      );
    }
  }
}
