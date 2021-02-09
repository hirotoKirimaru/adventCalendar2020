package com.example.demo.repository;

import com.example.demo.repository.dto.DummyDto;
import com.example.demo.repository.dto.UserDto;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;

@MybatisTest
public class CommonSetup {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  DataSource dataSource;

  SimpleJdbcInsert simpleJdbcInsert;


  public enum DbName {
    LOGIN_USER("login_user"),
    DUMMY("dummy"),

    ;

    String table;

    DbName(String table) {
      this.table = table;
    }

    public String getTable() {
      return table;
    }
  }

  protected void insertDummy(DummyDto... userDtoList) {
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName(DbName.DUMMY.getTable())
    ;
    for (var userDto : userDtoList) {
      var param = new HashMap<String, Object>();
      param.put("id_first", userDto.getId().getIdFirst());
      param.put("id_second", userDto.getId().getIdSecond());

      this.simpleJdbcInsert.execute(param);
    }

  }

}
