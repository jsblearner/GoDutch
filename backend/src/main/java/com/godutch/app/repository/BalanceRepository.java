package com.godutch.app.repository;

import com.godutch.app.entity.Balance;
import com.godutch.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    
    List<Balance> findByDebtorId(Long debtorId);
    
    List<Balance> findByCreditorId(Long creditorId);
    
    List<Balance> findByDebtorIdOrCreditorId(Long debtorId, Long creditorId);
    
    @Query("SELECT SUM(b.amount) FROM Balance b WHERE b.debtor.id = :userId")
    BigDecimal getTotalOwedByUser(@Param("userId") Long userId);
    
    @Query("SELECT SUM(b.amount) FROM Balance b WHERE b.creditor.id = :userId")
    BigDecimal getTotalOwedToUser(@Param("userId") Long userId);
    
    @Query("SELECT b FROM Balance b WHERE (b.debtor.id = :userId1 AND b.creditor.id = :userId2) OR (b.debtor.id = :userId2 AND b.creditor.id = :userId1)")
    List<Balance> findBalancesBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
