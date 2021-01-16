package com.example.demo.repository;


import com.example.demo.constant.CodeConstant;
import com.example.demo.repository.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {
  @Select("SELECT * FROM LOGIN_USER WHERE user_id=#{username}")
  UserDto findByUserName(String username);


  @Select("SELECT * FROM LOGIN_USER WHERE role=#{role}")
  List<UserDto> findByRole(@Param("role") CodeConstant.Role role);
}
