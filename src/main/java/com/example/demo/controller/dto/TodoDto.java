package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    @Min(1) @Max(1000)
    @NotNull
    int id;
    @NotNull
    String userId;
    @NotNull
    String action;
}
