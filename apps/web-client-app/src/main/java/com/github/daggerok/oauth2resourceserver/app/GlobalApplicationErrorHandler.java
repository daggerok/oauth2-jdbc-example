package com.github.daggerok.oauth2resourceserver.app;

import io.vavr.control.Try;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalApplicationErrorHandler {

  @SneakyThrows
  @ExceptionHandler(Throwable.class)
  public void handleErrors(final HttpServletResponse response, final Throwable error) {
    final String message = Try.of(error::getLocalizedMessage).getOrElseGet(throwable -> "Unknown error");
    log.error("handling: {}", message);
    response.sendRedirect("/");
  }
}
