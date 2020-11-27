package com.example.demo.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserRepository {
//  @Select("SELECT * FROM LOGIN_USER WHERE id=#{}")
//  public User find(User user);
}
