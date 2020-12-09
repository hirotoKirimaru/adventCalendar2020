package com.example.demo.external.operation;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RestOperationFactory {
  private final RestTemplateBuilder restTemplateBuilder;
  public RestOperations createRestOperations(ExternalProperties props) {
    RestTemplate build = restTemplateBuilder
        .setConnectTimeout(Duration.ofSeconds(props.getConnectionTimeout()))
        .setReadTimeout(Duration.ofSeconds(props.getReadTimeout()))
        .build();

    return build;
  }
}
