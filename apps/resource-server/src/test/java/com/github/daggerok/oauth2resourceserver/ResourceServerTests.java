package com.github.daggerok.oauth2resourceserver;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ResourceServer.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceServerTests {

  @Test
  public void contextLoads() { }
}
