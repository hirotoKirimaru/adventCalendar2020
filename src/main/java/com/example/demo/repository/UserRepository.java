package com.example.demo.repository;


import com.example.demo.repository.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserRepository {
  @Select("SELECT * FROM LOGIN_USER WHERE user_id=#{username}")
  UserDto findByUserName(String username);
}
