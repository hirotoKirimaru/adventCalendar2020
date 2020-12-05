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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerRepositoryTests {
  @Mock
  RepositoryForTest repository;
  RepositoryForTest proxy;

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
        .when(repository).execute();

    assertThatThrownBy(() -> proxy.execute())
        .isInstanceOf(DataAccessException.class);
  }

  @Test
  void test_01() {
    Mockito.doThrow(new NullPointerException()).when(repository).execute();

    assertThatThrownBy(() -> proxy.execute())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void success() {
    Object obj = new Object();
    Mockito.when(repository.execute()).thenReturn(obj);

    final Object actual = proxy.execute();
    assertThat(actual).isSameAs(obj);
  }


}
