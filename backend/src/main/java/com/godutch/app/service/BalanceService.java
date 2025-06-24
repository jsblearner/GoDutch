package com.godutch.app.service;

import com.godutch.app.entity.Balance;
import com.godutch.app.entity.User;
import com.godutch.app.repository.BalanceRepository;
import com.godutch.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    
    @Autowired
    private BalanceRepository balanceRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Balance createBalance(Long debtorId, Long creditorId, BigDecimal amount, String description) {
        Optional<User> debtor = userRepository.findById(debtorId);
        Optional<User> creditor = userRepository.findById(creditorId);
        
        if (debtor.isPresent() && creditor.isPresent()) {
            Balance balance = new Balance(debtor.get(), creditor.get(), amount, description);
            return balanceRepository.save(balance);
        }
        throw new RuntimeException("User not found");
    }
    
    public List<Balance> getUserBalances(Long userId) {
        return balanceRepository.findByDebtorIdOrCreditorId(userId, userId);
    }
    
    public BigDecimal getTotalOwedByUser(Long userId) {
        BigDecimal owed = balanceRepository.getTotalOwedByUser(userId);
        return owed != null ? owed : BigDecimal.ZERO;
    }
    
    public BigDecimal getTotalOwedToUser(Long userId) {
        BigDecimal owedTo = balanceRepository.getTotalOwedToUser(userId);
        return owedTo != null ? owedTo : BigDecimal.ZERO;
    }
    
    public BigDecimal getNetBalance(Long userId) {
        BigDecimal owedTo = getTotalOwedToUser(userId);
        BigDecimal owed = getTotalOwedByUser(userId);
        return owedTo.subtract(owed);
    }
    
    public List<Balance> getBalancesBetweenUsers(Long userId1, Long userId2) {
        return balanceRepository.findBalancesBetweenUsers(userId1, userId2);
    }
}
