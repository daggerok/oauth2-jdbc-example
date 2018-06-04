package com.github.daggerok.cors.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FilterRegistrationBeanConfig {

  @Bean
  public FilterRegistrationBean corsFilter() {

    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    final CorsFilter corsFilter = new CorsFilter(source);
    final FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

    return bean;
  }
}
