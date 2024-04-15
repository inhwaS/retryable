package com.example.demo;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceClient {

    @Retryable(maxAttempts = 5, value = {WebClientException.class}, backoff = @Backoff(delay = 10000))
    public void invokeExternalService() {
        // Simulate web client invocation
        System.out.println("...Invoking external service via WebClient...");
        if (Math.random() < 0.7) {
            System.out.println("[FAILED!] Failed to connect to external service\n");
            throw new WebClientException("Failed to connect to external service");
        } else {
            System.out.println("[SUCCEEDED!]External service invocation successful\n");
        }
    }
}