package com.example.demo.external.dummyUser;

import com.example.demo.external.operation.RestOperationFactory;
import com.example.demo.external.operation.RestTemplateInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestOperations;

@SpringJUnitConfig(classes = {
    RestTemplateAutoConfiguration.class
})
class DummyUserClientImplTests {

  DummyUserClient target;

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @BeforeEach
  void setup(){
    DummyUserClientProperties properties = new DummyUserClientProperties();
    properties.setSchema("https");
    properties.setHost("randomuser.me");
    properties.setPort(0);
    properties.setPath("/api");

    RestOperationFactory restOperationFactory = new RestOperationFactory(restTemplateBuilder, new RestTemplateInterceptor());
    RestOperations restOperations = restOperationFactory.createRestOperations(properties);

    target = new DummyUserClientImpl(properties, restOperations);
  }

  @Test
  void test_01(){
    System.out.println(target.createDummyUser());
  }

}
