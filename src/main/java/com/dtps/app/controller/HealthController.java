package com.dtps.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, Object> health() {

        long memoryUsed = ManagementFactory
                .getMemoryMXBean()
                .getHeapMemoryUsage()
                .getUsed() / (1024 * 1024);

        return Map.of(
                "status", "UP",
                "jvmMemoryMB", memoryUsed
        );
    }
}
