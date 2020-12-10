package com.example.demo.external.zipCloud;

import com.example.demo.external.dto.ZipCloudDto;

public interface ZipCloudClient {
  ZipCloudDto getAddressByZipcode(String zipCode);
}
