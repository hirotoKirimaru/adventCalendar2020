package com.example.demo.controller;

import com.example.demo.controller.dto.TodoDto;
import com.example.demo.controller.security.AuthTargetUser;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
  private final TodoRepository todoRepository;
  private final HttpSession session;

  @GetMapping("/")
  public String index(Model model) {
    SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
    Authentication authentication = securityContext.getAuthentication();
    AuthTargetUser principal = (AuthTargetUser) authentication.getPrincipal();

    List<TodoDto> todos = todoRepository.findList(principal.getUsername());

    model.addAttribute("todos", todos);
    return "index";
  }

  @PostMapping(value = "/", params = "edit")
  public String edit(TodoDto model) {
    System.out.println(model);
    System.out.println("修正ボタンクリック");
    todoRepository.update(model);
    return "redirect:/";
  }

  @PostMapping(value = "/", params = "add")
  public String add(@Validated TodoDto model) {
    System.out.println(model);
    System.out.println("追加ボタンクリック");

    todoRepository.insert(model);
    return "redirect:/";
  }

  @PostMapping(value = "/", params = "delete")
  public String delete(TodoDto model) {
    System.out.println(model);
    System.out.println("削除ボタンクリック");
    todoRepository.delete(model);
    return "redirect:/";
  }

  @PostMapping(value = "/", params = "upload_file")
  public String uploadFile(@RequestParam("file") MultipartFile uploadFile) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream(), StandardCharsets.UTF_8))){
      String line;
      while ((line = br.readLine()) != null) {
        final String[] split = line.split(",");
        final TodoDto todo = TodoDto.builder().id(Integer.parseInt(split[0])).userId(split[1]).action(split[2]).build();
        todoRepository.insert(todo);
      }
    } catch (IOException e) {
      throw new RuntimeException("ファイルが読み込めません", e);
    }

    return "redirect:/";
  }
}
