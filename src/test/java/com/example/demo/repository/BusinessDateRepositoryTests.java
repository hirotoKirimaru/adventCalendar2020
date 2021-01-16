package com.example.demo.repository;

import com.example.demo.repository.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class BusinessDateRepositoryTests {
  @Autowired
  BusinessDateRepository businessDateRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void test_01(){
    int date = businessDateRepository.getBusinessDate();

    assertThat(date).isEqualTo(2000);

  }

}
