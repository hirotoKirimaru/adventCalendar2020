package com.example.demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DummyDto {
    DummyIdDto id;
    String fieldFirst;
    String fieldSecond;
    String fieldThird;
}
