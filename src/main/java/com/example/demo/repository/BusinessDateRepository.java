package com.example.demo.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BusinessDateRepository {
  @Select("SELECT * FROM BUSINESS_DATE")
  int getBusinessDate();
}
