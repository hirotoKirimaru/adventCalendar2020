package com.example.demo.repository;


import com.example.demo.constant.CodeConstant;
import com.example.demo.repository.dto.DummyDto;
import com.example.demo.repository.dto.DummyIdDto;
import com.example.demo.repository.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DummyRepository {
  @Results(id = "Dummy", value={
      @Result(column = "id_first", property = "id.idFirst", id = true),
      @Result(column = "id_second", property = "id.idSecond", id = true),
  }  )
  @Select("SELECT * FROM DUMMY WHERE id_first=#{id.idFirst} AND id_second=#{id.idSecond}")
  DummyDto findByPrimaryKey(@Param("id") DummyIdDto id);

}
