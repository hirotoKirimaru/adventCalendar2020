package com.example.demo.external.operation;

import com.example.demo.external.dummyUser.DummyUserClientProperties;
import com.example.demo.external.zipCloud.ZipCloudClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
@RequiredArgsConstructor
public class RestOperationConfig {
  private final RestOperationFactory restOperationFactory;

  @Bean
  RestOperations dummyUserRestOperations(DummyUserClientProperties props){
    return restOperationFactory.createRestOperations(props);
  }

  @Bean
  RestOperations zipCloudRestOperations(ZipCloudClientProperties props){
    return restOperationFactory.createRestOperations(props);
  }
}
