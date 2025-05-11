# Bajaj Finserv Qualifier 1 -Submission

## Overview
This project is a solution for the Bajaj Finserv Health Qualifier 1 Java challenge. It uses Spring Boot with `RestTemplate` for webhook interactions and a custom self-join SQL query to solve the employee age comparison problem.

## Submission Details
- GitHub Repository: https://github.com/ArchitTomar/Bajaj
- RAW JAR Link:https://drive.google.com/drive/folders/1OXGwDFNJ2GoAyquJfwzAvYZ150zNhfo7?usp=drive_link

## Approach
- Used `RestTemplate` for synchronous webhook interactions.
- Implemented a modular structure with `WebhookInitializer` and `WebhookSubmitter` services.
- Developed a unique SQL query using a self-join approach with custom aliases and personalized comments.

## Note
- Encountered a 404 error when sending a POST request to `https://bfhldevapigw.healthrx.co.in/hiring/generatewebhook/JAVA`. This might be due to the server endpoint being unavailable as of May 2025.
