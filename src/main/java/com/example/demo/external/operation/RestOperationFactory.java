package com.example.demo.external.operation;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RestOperationFactory {
  private final RestTemplateBuilder restTemplateBuilder;
  public RestOperations createRestOperations(ExternalProperties props) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));

    RestTemplate build = restTemplateBuilder
        .setConnectTimeout(Duration.ofSeconds(props.getConnectionTimeout()))
        .setReadTimeout(Duration.ofSeconds(props.getReadTimeout()))
        .build();
    build.getMessageConverters().add(converter);

    return build;
  }
}
