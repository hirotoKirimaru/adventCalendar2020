package com.example.demo.controller.anotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PrintableValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface PrintableCharValid {
  String message() default "印刷不可能な文字が含まれています。";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    PrintableCharValid[] value();
  }
}
