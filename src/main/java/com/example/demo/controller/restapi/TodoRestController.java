package com.example.demo.controller.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/todos")
public class TodoRestController {
  @GetMapping("")
  public String get()  {
    return "hogehoge";
  }
}
