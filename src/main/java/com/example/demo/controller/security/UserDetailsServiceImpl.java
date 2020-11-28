package com.example.demo.controller.security;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String username) {
    final UserDto userDto = userRepository.findByUserName(username);

    return new AuthTargetUser(new User(userDto.getUserId(), "{noop}" + userDto.getPassword(), Collections.emptyList()));
  }
}
