package com.github.daggerok.oauth2authserver;

import com.github.daggerok.jdbc.JdbcAutoConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = JdbcAutoConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcAutoConfigurationTests {

  @Test
  public void contextLoads() { }
}
