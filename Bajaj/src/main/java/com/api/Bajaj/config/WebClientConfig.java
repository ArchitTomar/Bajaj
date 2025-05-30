package com.api.Bajaj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient customWebClient() {
        return WebClient.builder()
                .baseUrl("https://bfhldevapigw.healthrx.co.in")
                .build();
    }
}