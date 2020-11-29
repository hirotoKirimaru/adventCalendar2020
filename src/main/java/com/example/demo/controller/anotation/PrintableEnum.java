package com.example.demo.controller.anotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintableEnum {
  A("-", "2d"),
//  B("ー", "30fc"),
  C("‐", "2010"),
  D("‑", "2011"),
  E("–", "2013"),
  F("—", "2014"),
  G("―", "2015"),
//  H("−", "2212"),
  J("ｰ", "ff70");
  private final String character;
  private final String unicode;


  public static boolean checkIncludeIllegalChar(String unicode) {
    for (var value : PrintableEnum.values()) {
      if (value.unicode.equals(unicode)) {
        return true;
      }
    }
    return false;
  }
}
