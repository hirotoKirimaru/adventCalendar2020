package com.example.demo.controller.restapi;

import com.example.demo.external.dummyUser.DummyUserClient;
import com.example.demo.external.zipCloud.ZipCloudClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/external")
public class ExternalController {
  private final DummyUserClient dummyUserClient;
  private final ZipCloudClient zipCloudClient;

  @GetMapping("/dummy-user")
  public ResponseEntity<Object> createDummyUser() {
    return ResponseEntity.ok(dummyUserClient.createDummyUser());
  }

  @GetMapping("/zip-cloud")
  public ResponseEntity<Object> getZipCloud(@RequestParam("zipcode") String zipcode) {
    return ResponseEntity.ok(zipCloudClient.getAddressByZipcode(zipcode));
  }
}
