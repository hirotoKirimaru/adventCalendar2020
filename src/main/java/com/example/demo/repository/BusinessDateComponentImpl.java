package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BusinessDateComponentImpl implements BusinessDateComponent {
  private final BusinessDateRepository businessDateRepository;

  @Cacheable("businessDate")
  @Override
  public int getBusinessDate() {
    log.info("業務日付を取得します(ただし、呼ばれるということはキャッシュを読み込んでいない)");
    return businessDateRepository.getBusinessDate();
  }

  private final int ONE_DATE_MILLISECONDS = 86400000;
  @Scheduled(fixedDelay = ONE_DATE_MILLISECONDS) // 最後に実行してからNミリ秒
  @CacheEvict("businessDate")
  @Override
  public void deleteCache() {
    log.info("業務日付キャッシュを削除する");
  }

}
