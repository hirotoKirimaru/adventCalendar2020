package com.example.demo.controller;

import java.util.List;

import com.example.demo.controller.dto.TodoDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        List<TodoDto> todos = List.of(TodoDto.builder().id(1).userId("kirimaru").build(),
                TodoDto.builder().id(2).userId("kirimaru").build());

        model.addAttribute("message", "きり丸さんこんにちは");
        model.addAttribute("todos", todos);
        return "index";
    }

}