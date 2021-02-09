package com.example.demo.repository;

import com.example.demo.repository.dto.DummyDto;
import com.example.demo.repository.dto.UserDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public Map<String, Object> toMap(Serializable entity) {
    HashMap<String, Object> rtn = new HashMap<>();
    var source = new BeanPropertySqlParameterSource(entity);

    for (String readablePropertyName : source.getReadablePropertyNames()) {
      // クラス名は不要
      if (List.of("class", "blank", "bytes", "empty").contains(readablePropertyName)) {
        continue;
      }

      var snakeName = new PropertyNamingStrategy.SnakeCaseStrategy().translate(readablePropertyName);
      Object object = source.getValue(readablePropertyName);

      if (object instanceof Serializable) {
        var primaryKeyMap = toMap((Serializable) object);
        rtn.putAll(primaryKeyMap);
      }

      rtn.put(snakeName, object);
    }

    return rtn;
  }

}
