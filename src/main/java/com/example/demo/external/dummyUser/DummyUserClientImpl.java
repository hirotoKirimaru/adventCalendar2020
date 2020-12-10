package com.example.demo.external.dummyUser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component

public class DummyUserClientImpl implements DummyUserClient {
  private final DummyUserClientProperties properties;
  private final RestOperations restOperations;

  public DummyUserClientImpl(
      DummyUserClientProperties properties,
      @Qualifier("dummyUserRestOperations") RestOperations restOperations
  ) {
    this.properties = properties;
    this.restOperations = restOperations;
  }


  @Override
  public Object createDummyUser() {
    ResponseEntity<Object> responseEntity = this.restOperations.getForEntity(properties.getUrl(), Object.class);
    return responseEntity.getBody();
  }
}
