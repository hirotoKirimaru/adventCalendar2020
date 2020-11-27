package com.example.demo.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  private final String[] PERMITTED_URL = {"/"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests((requests) -> requests.anyRequest().authenticated());

    http.formLogin()
//        .loginPage("/login")
//        .loginProcessingUrl("/login")
//        .successForwardUrl("/")
//        .failureUrl("/login")
        .usernameParameter("loginId")
        .passwordParameter("password")
        .permitAll();

    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .deleteCookies("SESSION", "JSESSIONID")
//        .logoutUrl("/")
//        .logoutSuccessUrl("/")
        .invalidateHttpSession(true).permitAll();

    http.httpBasic();
  }



}
