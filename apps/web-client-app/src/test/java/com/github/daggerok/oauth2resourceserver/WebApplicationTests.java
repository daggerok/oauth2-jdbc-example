package com.github.daggerok.oauth2resourceserver;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebApplicationTests {

  @Test
  public void contextLoads() { }
}
