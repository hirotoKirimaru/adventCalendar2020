package com.example.demo.repository;

import com.example.demo.controller.dto.TodoDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TodoRepository {
    
    @Insert("INSERT INTO todo (id, user_id, action) VALUES (#{id}, #{userId}, #{action})")
    void insert(TodoDto todo);

    @Select("SELECT * FROM todo WHERE id = #{id} AND user_id = #{userId}")
    TodoDto select(int id, String userId);
}