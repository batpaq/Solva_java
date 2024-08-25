package com.batpaq.spring.springboot.solva.dao;

import com.batpaq.spring.springboot.solva.entity.Transaction;
import com.batpaq.spring.springboot.solva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLimitExceededTrue();
    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND FUNCTION('MONTH', t.transactionDate) = :month AND FUNCTION('YEAR', t.transactionDate) = :year")
    List<Transaction> findByUserAndMonth(@Param("user") User user, @Param("month") int month, @Param("year") int year);
}
