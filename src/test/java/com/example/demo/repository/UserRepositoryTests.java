package com.example.demo.repository;

import com.example.demo.repository.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
    jdbcTemplate.execute("INSERT INTO LOGIN_USER VALUES ('test', 'password', '0')");

    final UserDto expected = UserDto.builder().userId("test").password("password").build();
    final UserDto user = userRepository.findByUserName("test");

    assertThat(user).isEqualTo(expected);

  }


  @Test
  public void test_03(){
    List<UserDto> expected =
        List.of(UserDto.builder().userId("admin").password("pass").role("1").build());
    List<UserDto> byRole = userRepository.findByRole(UserRepository.Role.ADMIN);

    assertThat(byRole).isEqualTo(expected);

  }
}
