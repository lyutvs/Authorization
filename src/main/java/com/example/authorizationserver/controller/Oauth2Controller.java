package com.example.authorizationserver.controller;

import com.example.authorizationserver.model.OAuthToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

    private final Gson gson;
    private final RestTemplate restTemplate;

    @GetMapping(value = "/callback")
    public OAuthToken callbackSocial(@RequestParam String code) {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    }
}

