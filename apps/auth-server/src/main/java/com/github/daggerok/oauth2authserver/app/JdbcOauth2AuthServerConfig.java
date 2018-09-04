package com.github.daggerok.oauth2authserver.app;

import com.github.daggerok.cors.config.FilterRegistrationBeanConfig;
import com.github.daggerok.props.config.ClientsProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

//tag::jdbc-oauth2-auth-server-config[]
/**
 * CORS Filter.
 *
 * https://github.com/spring-projects/spring-security-oauth/issues/938#issuecomment-286243307
 * @Orger(2)
 * +
 * @Bean FilterRegistrationBean corsFilter() { ... }
 */
@Order(2)
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
@Import(FilterRegistrationBeanConfig.class) // re-use CORS filter module
public class JdbcOauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  final DataSource dataSource;
  final PasswordEncoder encoder;
  final ClientsProps clientsProps;
  final AuthenticationManager authenticationManager;

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource);
  }

  @Override
  public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {

    final ClientsProps.Client resourceAppClient = clientsProps.getResourceAppClient();
    final ClientsProps.Client passwordClient = clientsProps.getPasswordClient();

    //@formatter:off
    clients
        .jdbc(dataSource)
          .withClient(resourceAppClient.getClientId())
            .authorizedGrantTypes(resourceAppClient.getAuthorizedGrantTypes())
            .secret(resourceAppClient.generateSecret(encoder))
            .scopes(resourceAppClient.getScopes())
            .autoApprove(resourceAppClient.isAutoApprove())
            .and()
          .withClient(passwordClient.getClientId())
            .authorizedGrantTypes(passwordClient.getAuthorizedGrantTypes())
            .secret(passwordClient.generateSecret(encoder))
            .scopes(passwordClient.getScopes())
            .autoApprove(passwordClient.isAutoApprove())
    //@formatter:on
    ;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(tokenStore())
        .authenticationManager(authenticationManager)
    ;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }
}
//end::jdbc-oauth2-auth-server-config[]
