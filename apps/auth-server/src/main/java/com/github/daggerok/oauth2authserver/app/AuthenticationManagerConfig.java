package com.github.daggerok.oauth2authserver.app;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .csrf()
          .disable()
        .headers()
          .frameOptions()
            .sameOrigin()
            .and()
        .authorizeRequests()
          .antMatchers("/login", "/h2-console/**").permitAll()
        .anyRequest()
          .fullyAuthenticated()
        //.and()
        //  .logout()
        //    .clearAuthentication(true)
        //    .invalidateHttpSession(true)
        //    .deleteCookies("JSESSIONID", "JSESSION", "SESSIONID", "SESSION")
        //    .permitAll(true)
    ;
    //@formatter:on
  }
}
//end::authentication-manager-config[]
