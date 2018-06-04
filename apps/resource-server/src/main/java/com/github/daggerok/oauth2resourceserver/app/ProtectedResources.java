package com.github.daggerok.oauth2resourceserver.app;

import io.vavr.collection.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class ProtectedResources {

  @GetMapping
  @PreAuthorize("#oauth2.hasScope('read')")
  public ResponseEntity api() {
    return ResponseEntity.ok(HashMap.of(
        "ololo", "trololo",
        "at", Instant.now()
    ).toJavaMap());
  }
}
