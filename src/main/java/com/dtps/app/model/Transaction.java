package com.dtps.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String receiver;
    private Double amount;

    public Transaction() {}

    public Transaction(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public Double getAmount() { return amount; }

    public void setSender(String sender) { this.sender = sender; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public void setAmount(Double amount) { this.amount = amount; }
}
