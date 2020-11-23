package com.example.demo.repository;

import com.example.demo.controller.dto.TodoDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TodoRepository {
    
    @Insert("INSERT INTO todos (id, user_id, action) VALUES (#{id}, #{userId}, #{action})")
    public void insert(TodoDto todo);

    @Select("SELECT * FROM todos WHERE id = #{id} AND user_id = #{userId}")
    public TodoDto select(int id, String userId);
}
