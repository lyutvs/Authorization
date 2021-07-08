package com.example.authorizationserver.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

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
}
