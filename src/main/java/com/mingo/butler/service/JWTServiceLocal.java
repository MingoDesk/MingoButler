package com.mingo.butler.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Profile("dev")
@Service
public class JWTServiceLocal implements JWTService {

    private final RestTemplate restTemplate;
    private final String validateTokenUrl;

    public JWTServiceLocal(RestTemplateBuilder restTemplateBuilder,
                           @Value("${com.mingo.butler.validate-token-url}") String validateTokenUrl) {
        this.restTemplate = restTemplateBuilder
                .build();
        this.validateTokenUrl = validateTokenUrl;
    }

    @Override
    public boolean isTokenValid(String token) {
        String tokenFromValidation =  restTemplate.exchange(validateTokenUrl, HttpMethod.POST, null,
                new ParameterizedTypeReference<String>() {}, token).getBody();

        return token.equals(tokenFromValidation);
    }
}
