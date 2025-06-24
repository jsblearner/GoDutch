package com.godutch.app.controller;

import com.godutch.app.entity.Balance;
import com.godutch.app.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/balances")
@CrossOrigin(origins = "http://localhost:3000")
public class BalanceController {
    
    @Autowired
    private BalanceService balanceService;
    
    @PostMapping("/create")
    public ResponseEntity<Balance> createBalance(@RequestBody CreateBalanceRequest request) {
        try {
            Balance balance = balanceService.createBalance(
                request.getDebtorId(),
                request.getCreditorId(),
                request.getAmount(),
                request.getDescription()
            );
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Balance>> getUserBalances(@PathVariable Long userId) {
        List<Balance> balances = balanceService.getUserBalances(userId);
        return ResponseEntity.ok(balances);
    }
    
    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<BalanceSummary> getBalanceSummary(@PathVariable Long userId) {
        BigDecimal totalOwed = balanceService.getTotalOwedByUser(userId);
        BigDecimal totalOwedTo = balanceService.getTotalOwedToUser(userId);
        BigDecimal netBalance = balanceService.getNetBalance(userId);
        
        BalanceSummary summary = new BalanceSummary(totalOwed, totalOwedTo, netBalance);
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/between/{userId1}/{userId2}")
    public ResponseEntity<List<Balance>> getBalancesBetweenUsers(
            @PathVariable Long userId1, 
            @PathVariable Long userId2) {
        List<Balance> balances = balanceService.getBalancesBetweenUsers(userId1, userId2);
        return ResponseEntity.ok(balances);
    }
    
    // Inner classes for request/response
    public static class CreateBalanceRequest {
        private Long debtorId;
        private Long creditorId;
        private BigDecimal amount;
        private String description;
        
        // Getters and Setters
        public Long getDebtorId() { return debtorId; }
        public void setDebtorId(Long debtorId) { this.debtorId = debtorId; }
        
        public Long getCreditorId() { return creditorId; }
        public void setCreditorId(Long creditorId) { this.creditorId = creditorId; }
        
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
    
    public static class BalanceSummary {
        private BigDecimal totalOwed;
        private BigDecimal totalOwedTo;
        private BigDecimal netBalance;
        
        public BalanceSummary(BigDecimal totalOwed, BigDecimal totalOwedTo, BigDecimal netBalance) {
            this.totalOwed = totalOwed;
            this.totalOwedTo = totalOwedTo;
            this.netBalance = netBalance;
        }
        
        // Getters
        public BigDecimal getTotalOwed() { return totalOwed; }
        public BigDecimal getTotalOwedTo() { return totalOwedTo; }
        public BigDecimal getNetBalance() { return netBalance; }
    }
}
