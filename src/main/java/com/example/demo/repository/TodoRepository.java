package com.example.demo.repository;

import java.util.List;

import com.example.demo.controller.dto.TodoDto;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TodoRepository {

    @Insert("INSERT INTO todos (id, user_id, action) VALUES (#{id}, #{userId}, #{action})")
    public void insert(TodoDto todo);

    @Update("UPDATE todos SET id=#{id}, user_id=#{userId}, action=#{action} where id = #{id} AND user_id = #{userId} ")
    public void update(TodoDto todo);

    @Select("SELECT * FROM todos WHERE user_id = #{userId}")
    public List<TodoDto> findList(String userId);

    @Delete("DELETE FROM todos WHERE id=#{id}AND user_id = #{userId}")
    void delete(TodoDto todoDto);
}
