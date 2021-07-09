package com.example.authorizationserver.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;


/**
 * id/password 기반 Oauth2 인증을 담당하는 서버
 * 다음 endpont가 자동 생성 된다.
 * /oauth/authorize
 * /oauth/token
 */
@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer

public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;

    /**
     * 클라이언트 정보 주입 방식을 jdbcdetail로 변경
     *
     */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    /**
     * 토큰 정보를 DB를 통해 관리한다.
     *
     * @return
     */

    //    @Override
    //    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //        endpoints.tokenStore(new JdbcTokenStore(dataSource));
    //    }

    /**
     * 토큰 발급 방식을 JWT 토큰 방식으로 변경한다. 이렇게 하면 토큰 저장하는 DB Table은 필요가 없다.
     *
     * @param endpoints
     * @throws Exception
     */

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            super.configure(endpoints);
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
    }

    private AccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }


}
