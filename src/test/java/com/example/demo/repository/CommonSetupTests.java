package com.example.demo.repository;

import com.example.demo.repository.dto.DummyDto;
import com.example.demo.repository.dto.DummyIdDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommonSetupTests {
  CommonSetup target = new CommonSetup();

  @DisplayName(
      """
            ・Mapのkeyはキャメルケースで設定すること。
            ・Mapのvalueはエンティティを利用すること。
            ・主キー項目が複合主キーの場合でもマッピングできること
            ・その他の項目はマッピングされないこと            
            ・isXXXがマッピングされないこと
          """
  )
  @Test
  void test_01() {
    String idFirst = "111";
    String idSecond = "222";
    String fieldFirst = "1";
    String fieldSecond = "2";
    String fieldThird = "3";

    DummyIdDto dummyIdDto = DummyIdDto.builder()
        .idFirst(idFirst)
        .idSecond(idSecond)
        .build();

    DummyDto dummyDto = DummyDto.builder()
        .id(dummyIdDto)
        .fieldFirst(fieldFirst)
        .fieldSecond(fieldSecond)
        .fieldThird(fieldThird)
        .build();

    Map<String, Object> actual = target.toMap(dummyDto);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).hasSize(6);
    softly.assertThat(actual).containsEntry("id_first", idFirst);
    softly.assertThat(actual).containsEntry("id_second", idSecond);
    softly.assertThat(actual).containsEntry("field_first", fieldFirst);
    softly.assertThat(actual).containsEntry("field_second", fieldSecond);
    softly.assertThat(actual).containsEntry("field_third", fieldThird);
    softly.assertAll();
  }
}
