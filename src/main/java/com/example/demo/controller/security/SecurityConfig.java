package com.example.demo.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests((requests) -> requests.anyRequest().authenticated());

    http.formLogin();

//    http.logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .deleteCookies("SESSION", "JSESSIONID")
//        .invalidateHttpSession(true)
//        .permitAll();

    http.httpBasic();
  }



}
