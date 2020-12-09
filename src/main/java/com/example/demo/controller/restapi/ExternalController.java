package com.example.demo.controller.restapi;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.external.dummyUser.DummyUserClient;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/external")
public class ExternalController {
  private final DummyUserClient dummyUserClient;

  @GetMapping("/dummy-user")
  public ResponseEntity<Object> createDummyUser() {
    return ResponseEntity.ok(dummyUserClient.createDummyUser());
  }
}
