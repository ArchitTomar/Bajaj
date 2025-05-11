package com.api.Bajaj.service;



import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookSubmitter {

    private final WebClient webClient;

    public WebhookSubmitter(WebClient webClient) {
        this.webClient = webClient;
        System.out.println("WebhookSubmitter bean created for Archit Tomar's Qualifier 1 submission.");
    }

    public void submitSolution(String webhookUrl, String accessToken) {
        String uniqueSqlQuery = "-- Personalized query for Bajaj Qualifier 1 by Archit Tomar\n" +
                "-- Using a self-join to compare employee ages within departments\n" +
                "SELECT \n" +
                "    emp_main.EMP_ID AS employee_id_alias,\n" +
                "    emp_main.FIRST_NAME AS fname_custom,\n" +
                "    emp_main.LAST_NAME AS lname_custom,\n" +
                "    dept_ref.DEPARTMENT_NAME AS dept_name_alias,\n" +
                "    COUNT(emp_comp.EMP_ID) AS younger_count_custom\n" +
                "FROM \n" +
                "    EMPLOYEE emp_main\n" +
                "    JOIN DEPARTMENT dept_ref ON emp_main.DEPARTMENT = dept_ref.DEPARTMENT_ID\n" +
                "    LEFT JOIN EMPLOYEE emp_comp ON emp_main.DEPARTMENT = emp_comp.DEPARTMENT \n" +
                "    AND emp_comp.DOB > emp_main.DOB\n" +
                "GROUP BY \n" +
                "    emp_main.EMP_ID, emp_main.FIRST_NAME, emp_main.LAST_NAME, dept_ref.DEPARTMENT_NAME\n" +
                "ORDER BY \n" +
                "    emp_main.EMP_ID DESC";

        Map<String, String> submissionPayload = new HashMap<>();
        submissionPayload.put("finalQuery", uniqueSqlQuery);

        Mono<String> submissionMono = webClient.post()
                .uri("/hiring/testwebhook/JAVA")
                .header("Authorization", accessToken)
                .bodyValue(submissionPayload)
                .retrieve()
                .bodyToMono(String.class);

        submissionMono.subscribe(
                response -> System.out.println("Solution submitted successfully: " + response),
                error -> System.err.println("Error submitting solution: " + error.getMessage())
        );
    }
}