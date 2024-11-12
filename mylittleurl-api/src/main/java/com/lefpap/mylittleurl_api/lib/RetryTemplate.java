package com.lefpap.mylittleurl_api.lib;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

@Slf4j
@Builder
@Getter
public class RetryTemplate {

    public static final int DEFAULT_MAX_ATTEMPTS = 3;
    public static final int DEFAULT_BACKOFF_INTERVAL = 0;
    public static final double DEFAULT_BACKOFF_MULTIPLIER = 1.0;
    public static final long DEFAULT_MAX_BACKOFF_INTERVAL = Long.MAX_VALUE;

    @Builder.Default
    private int maxAttempts = DEFAULT_MAX_ATTEMPTS;
    @Builder.Default
    private long backOffInterval = DEFAULT_BACKOFF_INTERVAL;
    @Builder.Default
    private double multiplier = DEFAULT_BACKOFF_MULTIPLIER;
    @Builder.Default
    private long maxBackOffInterval = DEFAULT_MAX_BACKOFF_INTERVAL;
    @Singular
    private Set<Class<? extends Exception>> retryableExceptions;

    public <T> T execute(IntFunction<T> operation) {
        int attempt = 1;
        long currentInterval = backOffInterval;

        while (true) {
            try {
                return operation.apply(attempt);
            } catch (Exception e) {
                if (attempt >= maxAttempts || !shouldRetryOn(e)) {
                    // Rethrow the exception if max attempts are reached
                    // Or if the exception is not retryable
                    throw e;
                }

                log.warn("Retry attempt {} for exception: {} - Retrying after {} ms", attempt, e.getMessage(), currentInterval);

                attempt++;
                applyBackOff(currentInterval);

                // Update interval for exponential backoff if multiplier > 1
                currentInterval = (multiplier > 1.0)
                    ? Math.min((long)(currentInterval * multiplier), maxBackOffInterval)
                    : currentInterval;
            }
        }
    }

    public void executeOnly(IntConsumer operation) {
        execute(attempt -> {
            operation.accept(attempt);
            return null;
        });
    }

    private boolean shouldRetryOn(Exception e) {
        return retryableExceptions.isEmpty() || retryableExceptions.stream().anyMatch(clazz -> clazz.isInstance(e));
    }

    private void applyBackOff(long interval) {
        if (interval <= 0) {
            return;
        }

        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
