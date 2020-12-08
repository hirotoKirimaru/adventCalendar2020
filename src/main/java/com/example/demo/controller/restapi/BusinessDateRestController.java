package com.example.demo.controller.restapi;

import com.example.demo.repository.BusinessDateComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/businessDate")
public class BusinessDateRestController {
  private final BusinessDateComponent businessDateComponent;

  @GetMapping("/")
  public ResponseEntity<Integer> get() {
    log.info("getメソッド呼びました");
    int businessDate = businessDateComponent.getBusinessDate();
    return ResponseEntity.ok(businessDate);
  }

  @GetMapping("/clear")
  public ResponseEntity<?> delete() {
    log.info("deleteメソッド呼びました");
    businessDateComponent.deleteCache();
    return ResponseEntity.ok().build();
  }
}
