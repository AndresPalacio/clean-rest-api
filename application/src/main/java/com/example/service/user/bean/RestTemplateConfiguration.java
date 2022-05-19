package com.example.service.user.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private static final int DEFAULT_TIME_OUT = 5000;
    private static final int STANDARD_TIME_OUT = 5000;
    private static final int MAX_TIME_OUT = 60000;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.createRequestFactory());
        return new RestTemplate();
    }

    private HttpComponentsClientHttpRequestFactory createRequestFactory() {
        final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(DEFAULT_TIME_OUT);
        httpRequestFactory.setConnectTimeout(STANDARD_TIME_OUT);
        httpRequestFactory.setReadTimeout(MAX_TIME_OUT);
        return httpRequestFactory;
    }
}
