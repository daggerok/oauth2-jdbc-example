package com.github.daggerok.oauth2authserver.app;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//tag::authentication-manager-config[]
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = JdbcUserDetailsService.class)
public class AuthenticationManagerConfig extends WebSecurityConfigurerAdapter {

  final JdbcUserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
//end::authentication-manager-config[]
