package com.example.demo.controller;

import java.util.List;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.repository.TodoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final TodoRepository TodoRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<TodoDto> todos = List.of(TodoDto.builder().id(1).userId("kirimaru").build(),
                TodoDto.builder().id(2).userId("kirimaru").action("郵便局に行く").build());

        model.addAttribute("message", "きり丸さんこんにちは");
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping(value="/", params="edit")
    public String edit(TodoDto model){
        System.out.println(model);
        System.out.println("修正ボタンクリック");
        return "redirect:/";
    }

    @PostMapping(value="/", params="add")
    public String add(TodoDto model){
        System.out.println(model);
        System.out.println("追加ボタンクリック");
        
        todoRepository.insert(model);
        return "redirect:/";
    }

        @PostMapping(value="/", params="delete")
    public String delete(TodoDto model){
        System.out.println(model);
        System.out.println("削除ボタンクリック");
        return "redirect:/";
    }
}