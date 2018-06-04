package com.github.daggerok.oauth2resourceserver.app;

import com.github.daggerok.props.config.AppProps;
import io.vavr.collection.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class IndexPage {

  final AppProps appProps;

  @GetMapping({ "", "/", "/404", "/error" })
  public String index() {
    return "index";
  }

  @ResponseBody
  @GetMapping("/api/config")
  public ResponseEntity config() {
    return ResponseEntity.ok(HashMap.of(
        "appProps", appProps
    ).toJavaMap());
  }
}
