package com.example.demo.controller.anotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PrintableValidatorTests {

  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @ArgumentsSource(Target.class)
  @ParameterizedTest
  void printableTest(Target target) {
    TestBean testBean = new TestBean(target.string);
    Set<ConstraintViolation<TestBean>> violations = validator.validate(testBean);
    assertThat(violations.isEmpty()).isEqualTo(target.expected);
  }

  static class Target implements ArgumentsProvider {
    private String string;
    private boolean expected;
    Target(){
    }

    Target(String string, boolean expected){
      this.string = string;
      this.expected = expected;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
      return Stream.of(
          new Target("-", false),
          new Target("ー", true),
          new Target("‐", false),
          new Target("‑", false),
          new Target("–", false),
          new Target("—", false),
          new Target("―", false),
          new Target("−", true),
          new Target("ｰ", false)
      ).map(Arguments::of);
    }
  }

  private static class TestBean {
    @PrintableCharValid
    private String targetStr;

    TestBean(String targetStr) {
      this.targetStr = targetStr;
    }
  }
}
