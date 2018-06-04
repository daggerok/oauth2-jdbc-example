package com.github.daggerok.oauth2resourceserver;

import com.github.daggerok.cors.CorsAutoConfiguration;
import com.github.daggerok.props.AppPropsAutoConfiguration;
import com.github.daggerok.props.ClientsPropsAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
    CorsAutoConfiguration.class,
    AppPropsAutoConfiguration.class,
    ClientsPropsAutoConfiguration.class,
})
@SpringBootApplication
public class ResourceServer {

  public static void main(String[] args) {
    SpringApplication.run(ResourceServer.class, args);
  }
}
