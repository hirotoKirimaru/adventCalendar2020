package com.example.demo.repository;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@MybatisTest
public class CommonSetup {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  DataSource dataSource;

  SimpleJdbcInsert simpleJdbcInsert;



  public enum DbName {
    LOGIN_USER("login_user");

    String table;

    DbName(String table) {
      this.table = table;
    }

    public String getTable() {
      return table;
    }
  }

}
