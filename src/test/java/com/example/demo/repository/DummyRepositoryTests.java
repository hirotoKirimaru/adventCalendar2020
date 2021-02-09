package com.example.demo.repository;

import com.example.demo.repository.dto.DummyDto;
import com.example.demo.repository.dto.DummyIdDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DummyRepositoryTests extends CommonSetup {
  @Autowired
  DummyRepository dummyRepository;

  DummyIdDto DUMMY_DTO_ID_1 = DummyIdDto.builder()
      .idFirst("111")
      .idSecond("222")
      .build();

  DummyDto DUMMY_DTO_1 = DummyDto.builder()
      .id(DUMMY_DTO_ID_1)
      .fieldFirst("1")
      .fieldSecond("2")
      .fieldThird("3")
      .build();

  @Test
  public void test_01() {
    insertDummy(DUMMY_DTO_1);

    DummyDto actual = dummyRepository.findByPrimaryKey(DUMMY_DTO_ID_1);

    assertThat(actual).isEqualTo(DUMMY_DTO_1);
  }

}
