package com.config;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class TestHealthEndPoint implements Endpoint<Health> {
    @Override
    public String getId() {
        return "testHealth";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Health invoke() {
        return Health.up().withDetail("message", "Custom health check is OK").build();
    }
}
