package com.example.demo.controller.security;

import org.springframework.security.core.userdetails.User;

public class AuthTargetUser extends org.springframework.security.core.userdetails.User{
  private User user;

  public AuthTargetUser(User user) {
    super(user.getUsername(), user.getPassword(), user.getAuthorities());
    this.user = user;
  }
}
