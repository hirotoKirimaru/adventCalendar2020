package com.example.demo.controller;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final TodoRepository todoRepository;

    @GetMapping("/login")
    public String index(Model model) {
        return "modules/login/login";
    }

    @PostMapping("/login")
    public String edit(TodoDto model){
        return "redirect:/";
    }
}
