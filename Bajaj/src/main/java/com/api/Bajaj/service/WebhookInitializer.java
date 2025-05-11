package com.api.Bajaj.service;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookInitializer implements CommandLineRunner {

    private final WebClient webClient;
    private final WebhookSubmitter webhookSubmitter;

    public WebhookInitializer(WebClient webClient, WebhookSubmitter webhookSubmitter) {
        this.webClient = webClient;
        this.webhookSubmitter = webhookSubmitter;
        System.out.println("WebhookInitializer bean created for Archit Tomar's Qualifier 1 submission.");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initiating webhook generation for Archit Tomar's unique Qualifier 1 submission...");

        Map<String, String> payloadUnique = new HashMap<>();
        payloadUnique.put("name", "Archit Tomar");
        payloadUnique.put("regNo", "9993339096");
        payloadUnique.put("email", "archittomar220705@acropolis.in");

        Mono<Map> responseMono = webClient.post()
                .uri("/hiring/generatewebhook/JAVA")
                .bodyValue(payloadUnique)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorResume(e -> {
                    System.err.println("Failed to generate webhook: " + e.getMessage());
                    return Mono.empty();
                });

        responseMono.subscribe(response -> {
            if (response != null) {
                String webhookUrlUnique = (String) response.get("webhook");
                String accessTokenUnique = (String) response.get("accessToken");
                System.out.println("Webhook URL received: " + webhookUrlUnique);
                webhookSubmitter.submitSolution(webhookUrlUnique, accessTokenUnique);
            } else {
                System.out.println("No webhook response received; skipping submission.");
            }
        }, error -> {
            System.err.println("Error generating webhook: " + error.getMessage());
        });
    }
}