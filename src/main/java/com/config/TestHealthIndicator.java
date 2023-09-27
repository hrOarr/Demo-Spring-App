package com.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TestHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // Your custom health check logic goes here
        if (isCustomHealthCheckSuccessful()) {
            return Health.up()
                    .withDetail("message", "Custom health check is OK")
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Custom health check failed")
                    .build();
        }
    }

    private boolean isCustomHealthCheckSuccessful() {
        // Implement your custom health check logic here
        // Return true if the health check is successful, false otherwise
        return true; // Replace with your actual health check logic
    }
}
