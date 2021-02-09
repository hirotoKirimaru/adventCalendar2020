package com.example.demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DummyDto implements Serializable {
    DummyIdDto id;
    String fieldFirst;
    String fieldSecond;
    String fieldThird;
}
