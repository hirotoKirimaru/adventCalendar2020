package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BusinessDateComponentImpl implements BusinessDateComponent {
  private final BusinessDateRepository businessDateRepository;

  @Cacheable("businessDate")
  @Override
  public int getBusinessDate() {
    return businessDateRepository.getBusinessDate();
  }

  @CacheEvict("businessDate")
  @Override
  public void deleteCache() {
  }

}
