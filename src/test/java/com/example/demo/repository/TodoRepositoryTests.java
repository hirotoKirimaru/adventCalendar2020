package com.example.demo.repository;

import com.example.demo.controller.dto.TodoDto;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class TodoRepositoryTests {
    @Autowired
    TodoRepository todoRepository;

    // @Nested
    // class Insert {

    // }
    @Test
    public void test_01(){
        TodoDto todoDto = TodoDto.builder().id(1).userId("kirimaru").action("豆腐買う").build();

        todoRepository.insert(todoDto);

        
    }


}