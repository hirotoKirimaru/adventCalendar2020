package com.example.demo.controller;

import java.util.List;

import com.example.demo.controller.dto.TodoDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        List<TodoDto> todos = List.of(TodoDto.builder().id(1).userId("kirimaru").build(),
                TodoDto.builder().id(2).userId("kirimaru").action("郵便局に行く").build());

        model.addAttribute("message", "きり丸さんこんにちは");
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/")
    public String edit(TodoDto model){
        System.out.println(model);
        return "redirect:/";
    }


}