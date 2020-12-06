package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@JsonPropertyOrder({"ID", "UserId", "Action"})
public class TodoCsv {
  @JsonProperty("ID")
  private long id;
  @JsonProperty("UserId")
  private String userId;
  @JsonProperty("Action")
  private String action;

}
