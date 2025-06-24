package com.godutch.app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "balances")
public class Balance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "debtor_id", nullable = false)
    private User debtor; // Person who owes money
    
    @ManyToOne
    @JoinColumn(name = "creditor_id", nullable = false)
    private User creditor; // Person who is owed money
    
    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public Balance() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Balance(User debtor, User creditor, BigDecimal amount, String description) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getDebtor() {
        return debtor;
    }
    
    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }
    
    public User getCreditor() {
        return creditor;
    }
    
    public void setCreditor(User creditor) {
        this.creditor = creditor;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
