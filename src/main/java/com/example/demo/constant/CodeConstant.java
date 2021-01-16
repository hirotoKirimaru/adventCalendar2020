package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CodeConstant {
  @Getter
  @AllArgsConstructor
  public enum Role {
    IPPAN("IPPAN"),
    ADMIN("ADMIN"),
    MASTER("MASTER"),
    ;

    String role;
  }
}
