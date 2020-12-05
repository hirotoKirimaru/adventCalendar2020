package com.example.demo.repository.interceptor;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlerRepository {
  @AfterThrowing(
      value = "execution(* com.example.demo.repository..*(..))",
      throwing = "ex")
  public void dataAccessException(DataAccessException ex) {
    throw new com.example.demo.exception.DataAccessException(ex);
  }
}
