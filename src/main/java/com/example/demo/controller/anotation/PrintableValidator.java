package com.example.demo.controller.anotation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrintableValidator implements ConstraintValidator<PrintableCharValid, String> {
  @Override
  public void initialize(PrintableCharValid constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    for (char c : value.toCharArray()) {
      final String utf8 = Integer.toHexString(c);
      if (PrintableEnum.checkIncludeIllegalChar(utf8)){
        return false;
      }
    }
    return true;
  }
}


