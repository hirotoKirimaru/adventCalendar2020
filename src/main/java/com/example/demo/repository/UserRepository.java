package com.example.demo.repository;


import com.example.demo.repository.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface UserRepository {
  @Select("SELECT * FROM LOGIN_USER WHERE user_id=#{username}")
  UserDto findByUserName(String username);

  @Getter
  @AllArgsConstructor
  public enum Role{
    IPPAN("0"),
    ADMIN("1"),
    MASTER("2"),
    ;

    String role;
  }


  @Select("SELECT * FROM LOGIN_USER WHERE role='1'")
//  @Select("SELECT * FROM LOGIN_USER")
  List<UserDto> findByRole(@Param("role") Role role);
}
