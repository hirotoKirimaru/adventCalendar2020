package com.example.demo.external.zipCloud;

import com.example.demo.external.dto.ZipAddressDto;
import com.example.demo.external.dto.ZipCloudDto;
import com.example.demo.external.dummyUser.DummyUserClient;
import com.example.demo.external.dummyUser.DummyUserClientImpl;
import com.example.demo.external.dummyUser.DummyUserClientProperties;
import com.example.demo.external.operation.RestOperationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = {
    RestTemplateAutoConfiguration.class
})
class ZipCloudClientImplTests {

  ZipCloudClient target;

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @BeforeEach
  void setup(){
    ZipCloudClientProperties properties = new ZipCloudClientProperties();
    properties.setSchema("https");
    properties.setHost("zipcloud.ibsnet.co.jp");
    properties.setPort(0);
    properties.setPath("/api/search");

    target = new ZipCloudClientImpl(properties, restTemplateBuilder);
  }

  @Test
  void test_01(){
    ZipCloudDto expected = ZipCloudDto.builder()
        .message(null)
        .results(List.of(
            ZipAddressDto.builder()
                .address1("神奈川県")
                .address2("厚木市")
                .address3("中町")
                .kana1("ｶﾅｶﾞﾜｹﾝ")
                .kana2("ｱﾂｷﾞｼ")
                .kana3("ﾅｶﾁｮｳ")
                .prefcode("14")
                .zipcode("2430018")
                .build()
        ))
        .status("200")
        .build();

    ZipCloudDto actual = target.createDummyUser("2430018");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_02(){
    ZipCloudDto expected = ZipCloudDto.builder()
        .message("パラメータ「郵便番号」の桁数が不正です。")
        .status("400")
        .build();

    ZipCloudDto actual = target.createDummyUser("XXX");

    assertThat(actual).isEqualTo(expected);
  }

}
