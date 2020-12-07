package com.example.demo.controller.restapi;

import com.example.demo.repository.BusinessDateComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/businessDate")
public class BusinessDateRestController {
  private final BusinessDateComponent businessDateComponent;

  @GetMapping("/")
  public ResponseEntity<Integer> get() {
    int businessDate = businessDateComponent.getBusinessDate();
    return ResponseEntity.ok(businessDate);
  }

  @DeleteMapping("/")
  public ResponseEntity delete() {
    businessDateComponent.deleteCache();
    return ResponseEntity.ok().build();
  }
}
