package com.example.authorizationserver.config;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("testClientID")
                .secret("testSecret")
                .redirectUris("http://localhost:8081/oauth2/callback")
                .authorizedGrantTypes("authorization_code")
                .scopes("read", "writr")
                .accessTokenValiditySeconds(30000);
    }

}
