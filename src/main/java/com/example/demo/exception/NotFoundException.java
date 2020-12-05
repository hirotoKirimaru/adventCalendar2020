package com.example.demo.exception;

public class NotFoundException extends BusinessException {
  public NotFoundException(){
    super();
  }
  NotFoundException(Throwable ex){
    super(ex);
  }
}
