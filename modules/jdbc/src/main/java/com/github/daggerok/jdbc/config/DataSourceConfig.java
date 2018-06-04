package com.github.daggerok.jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class DataSourceConfig {
  //tag::datasource-initializer[]
  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    final DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator());
    return initializer;
  }

  private DatabasePopulator databasePopulator() {
    final ClassPathResource schema = new ClassPathResource("/schema-h2.sql", DataSourceConfig.class.getClassLoader());
    return new ResourceDatabasePopulator(false, true, UTF_8.displayName(), schema);
  }
  //end::datasource-initializer[]
}
