package com.example.demo.controller.restapi;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/todos")
public class TodoRestController {
  private final TodoRepository todoRepository;

  @GetMapping("/{userId}")
  public ResponseEntity<List<TodoDto>> get(@PathVariable String userId)  {

    final List<TodoDto> list = todoRepository.findList(userId);

    if (list.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }
}
