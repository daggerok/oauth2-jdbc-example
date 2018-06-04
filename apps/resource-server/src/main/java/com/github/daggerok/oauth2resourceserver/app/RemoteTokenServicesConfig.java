package com.github.daggerok.oauth2resourceserver.app;

import com.github.daggerok.props.config.AppProps;
import com.github.daggerok.props.config.ClientsProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import static java.lang.String.format;

@Configuration
@RequiredArgsConstructor
public class RemoteTokenServicesConfig {
  //tag::remote-token-services-config[]
  final AppProps appProps;
  final ClientsProps clientsProps;

  @Bean
  @Primary
  public RemoteTokenServices tokenService() {

    final String checkTokenEndpointUrl = format("%s/oauth/check_token", appProps.getAuthServerUrl());
    final RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
    final ClientsProps.Client client = clientsProps.getResourceAppClient();
    tokenService.setClientId(client.getClientId());
    tokenService.setClientSecret(client.getSecret());
    return tokenService;
  }
  //end::remote-token-services-config[]
}
