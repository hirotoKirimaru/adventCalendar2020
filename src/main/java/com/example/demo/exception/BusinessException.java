package com.example.demo.exception;

public abstract class BusinessException extends RuntimeException {
  BusinessException(){
    super();
  }
  BusinessException(Throwable ex){
    super(ex);
  }
}
