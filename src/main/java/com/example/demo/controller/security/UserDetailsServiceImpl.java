package com.example.demo.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new AuthTargetUser(new User("kirimaru","{noop}123456", Collections.emptyList()));
  }
}
