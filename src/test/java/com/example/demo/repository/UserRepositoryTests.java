package com.example.demo.repository;

import com.example.demo.repository.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
class UserRepositoryTests {
  @Autowired
  UserRepository userRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void test_01(){
        final UserDto user = userRepository.findByUserName("gorilla");

    assertThat(user).isNull();

  }

  @Test
  public void test_02(){
    jdbcTemplate.execute("INSERT INTO LOGIN_USER VALUES ('test', 'password')");

    final UserDto expected = UserDto.builder().userId("test").password("password").build();
    final UserDto user = userRepository.findByUserName("test");

    assertThat(user).isEqualTo(expected);

  }
}
