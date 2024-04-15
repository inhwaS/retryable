package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceClient.class);
    private int attempt = 1; // to track attempts manually

    @Retryable(value = WebClientException.class, maxAttempts = 4, backoff = @Backoff(delay = 3000))
    public void invokeExternalService() throws WebClientException {
        try {
            logger.info("Attempt #" + attempt + ": Trying to invoke external service...");

            if (Math.random() < 0.7) {
                throw new WebClientException("Failed to connect to external service");
            }

            logger.info("External service invocation successful on attempt #" + attempt);
        } catch (WebClientException e) {
            logger.error("Attempt #" + attempt + ": Failed - " + e.getMessage());
            attempt++;
            throw e;
        }
    }
}
