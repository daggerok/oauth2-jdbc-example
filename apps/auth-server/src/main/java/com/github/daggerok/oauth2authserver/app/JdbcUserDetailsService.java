package com.github.daggerok.oauth2authserver.app;

import io.vavr.collection.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

import static java.lang.String.format;

//tag::jdbc-user-details-service[]
@Service
@RequiredArgsConstructor
public class JdbcUserDetailsService implements UserDetailsService {

  // in memory applications credentials database configuration:
  private static final Map<String, String> db = HashMap.of(
      "usr", "pwd"
  ).toJavaMap();

  final PasswordEncoder encoder;
  final JdbcTemplate jdbcTemplate;

  @PostConstruct
  public void init() {
    db.forEach(this::mapToUser);
  }

  private UserDetails mapToUser(final String username, final String password) {
    return User
        .withUsername(username)
        .password(null == encoder ? format("{noop}%s", password) : encoder.encode(password))
        .disabled(false)
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .authorities("USER", "ADMIN", "ROLE_USER", "ROLE_ADMIN")
        .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Map.Entry<String, String> pair = db.entrySet()
                                             .parallelStream()
                                             .filter(e -> e.getKey().equals(username))
                                             .findFirst()
                                             .orElseThrow(() -> new UsernameNotFoundException(username));
    return mapToUser(pair.getKey(), pair.getValue());
  }
}
//end::jdbc-user-details-service[]
