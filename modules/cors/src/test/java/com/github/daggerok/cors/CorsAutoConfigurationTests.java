package com.github.daggerok.cors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = CorsAutoConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class CorsAutoConfigurationTests {

  @Test
  public void contextLoads() { }
}
