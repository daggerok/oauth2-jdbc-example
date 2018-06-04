package com.github.daggerok.oauth2authserver.app;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//tag::jdbc-user-details-service[]
@Service
@RequiredArgsConstructor
public class JdbcUserDetailsService implements UserDetailsService {

  final PasswordEncoder encoder;
  final JdbcTemplate jdbcTemplate;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //@formatter:off
    return User
        .withUsername("usr")
        //.password("pwd")
        .password(null == encoder ? "{noop}pwd" : encoder.encode("pwd"))
        .disabled(false)
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .authorities("USER", "ADMIN", "ROLE_USER", "ROLE_ADMIN")
        .build();
    //@formatter:on
  }
}
//end::jdbc-user-details-service[]
