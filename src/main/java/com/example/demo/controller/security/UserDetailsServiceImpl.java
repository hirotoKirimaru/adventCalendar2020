package com.example.demo.controller.security;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String username) {
    // TODO: userIdが取得できなかった。
    final UserDto userDto = userRepository.findByUserName(username);
    if (Objects.isNull(userDto)){
      throw new RuntimeException("ユーザ名かパスワードが正しくありません");
    }
    return new AuthTargetUser(new User(username, "{noop}" + userDto.getPassword(), Collections.emptyList()));
  }
}
