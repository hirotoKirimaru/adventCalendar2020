package com.example.demo.external.zipCloud;

import com.example.demo.external.anotation.ZipCloudRest;
import com.example.demo.external.dto.ZipCloudDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;

@Component
public class ZipCloudClientImpl implements ZipCloudClient {
  private final ZipCloudClientProperties props;
  private final RestOperations restOperations;

  public ZipCloudClientImpl(ZipCloudClientProperties props,
                            @ZipCloudRest RestOperations restOperations) {
    this.props = props;
    this.restOperations = restOperations;
  }

  @Override
  public ZipCloudDto getAddressByZipcode(String zipCode) {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("zipcode", zipCode);

    ResponseEntity<ZipCloudDto> responseEntity = restOperations.getForEntity(props.getUrl(hashMap), ZipCloudDto.class);
    return responseEntity.getBody();
  }
}
