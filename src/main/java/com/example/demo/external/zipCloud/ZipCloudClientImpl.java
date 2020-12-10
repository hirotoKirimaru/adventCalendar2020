package com.example.demo.external.zipCloud;

import com.example.demo.external.dto.ZipCloudDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Component

public class ZipCloudClientImpl implements ZipCloudClient {
  private final ZipCloudClientProperties props;
  private final RestTemplateBuilder restTemplateBuilder;

  public ZipCloudClientImpl(
      ZipCloudClientProperties props,
      RestTemplateBuilder restTemplateBuilder
  ) {
    this.props = props;
    this.restTemplateBuilder = restTemplateBuilder;
  }


  @Override
  public ZipCloudDto createDummyUser(String zipCode) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));

    RestTemplate restTemplate = restTemplateBuilder
        .setConnectTimeout(Duration.ofSeconds(props.getConnectionTimeout()))
        .setReadTimeout(Duration.ofSeconds(props.getReadTimeout()))
        .build();
    restTemplate.getMessageConverters().add(converter);

    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("zipcode", zipCode);

    ResponseEntity<ZipCloudDto> responseEntity = restTemplate.getForEntity(props.getUrl(hashMap), ZipCloudDto.class);
    return responseEntity.getBody();
  }
}
