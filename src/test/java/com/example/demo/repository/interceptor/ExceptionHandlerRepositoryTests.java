package com.example.demo.repository.interceptor;

import com.example.demo.exception.DataAccessException;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.test.RepositoryForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerRepositoryTests {

  ExceptionHandlerRepository target;

  @Mock
  TodoRepository repository;
  TodoRepository proxy;

  @BeforeEach
  void beforeEach() {
    AspectJProxyFactory factory = new AspectJProxyFactory(repository);
    ExceptionHandlerRepository target = new ExceptionHandlerRepository();
    factory.addAspect(target);
    proxy = factory.getProxy();
  }

  @Test
  void throwDataAccessExceptionTo() {
    Mockito.doThrow(new InvalidDataAccessResourceUsageException("test"))
        .when(repository).findList("kirimaru");

    assertThatThrownBy(() -> proxy.findList("kirimaru"))
        .isInstanceOf(DataAccessException.class);
  }

  @Test
  void test_01() {
    Mockito.doThrow(new NullPointerException()).when(repository).findList("kirimaru");

    assertThatThrownBy(() -> proxy.findList("kirimaru"))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void success() {

    Mockito.when(repository.findList("kirimaru")).thenReturn(List.of());

    final Object actual = proxy.findList("kirimaru");
    assertThat(actual).isSameAs(List.of());
  }


}
