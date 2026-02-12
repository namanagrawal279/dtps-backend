package com.dtps.app.controller;

import com.dtps.app.model.Transaction;
import com.dtps.app.repository.TransactionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/tx")
    @CircuitBreaker(name = "txService", fallbackMethod = "fallback")
    public Map<String, Object> createTransaction(@RequestBody Map<String, Object> request) {

        String sender = (String) request.get("sender");
        String receiver = (String) request.get("receiver");
        Double amount = Double.valueOf(request.get("amount").toString());

        Transaction tx = new Transaction(sender, receiver, amount);
        repository.save(tx);

        return Map.of(
                "transactionId", UUID.randomUUID().toString(),
                "status", "SUCCESS"
        );
    }

    public Map<String, Object> fallback(Map<String, Object> request, Exception ex) {
        return Map.of(
                "status", "FAILED",
                "message", "Service temporarily unavailable"
        );
    }
}
