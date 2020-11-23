package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    int id;
    String userId;
}
