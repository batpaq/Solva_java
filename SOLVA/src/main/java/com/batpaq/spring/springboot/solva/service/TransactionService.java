package com.batpaq.spring.springboot.solva.service;

import com.batpaq.spring.springboot.solva.entity.Transaction;
import com.batpaq.spring.springboot.solva.entity.ExpenseLimit;
import com.batpaq.spring.springboot.solva.dao.TransactionRepository;
import com.batpaq.spring.springboot.solva.dao.ExpenseLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExpenseLimitRepository expenseLimitRepository;

    public void saveTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        BigDecimal amount = transaction.getAmount();
        if (amount == null) {
            throw new IllegalArgumentException("Transaction amount cannot be null");
        }

        ExpenseLimit currentLimit = expenseLimitRepository.findTopByOrderByPeriodEndDesc();

        if (currentLimit != null) {
            BigDecimal limitAmount = currentLimit.getLimitAmount();
            if (limitAmount == null) {
                throw new IllegalArgumentException("Limit amount from ExpenseLimit cannot be null");
            }

            BigDecimal totalSpent = transactionRepository.findByUserAndMonth(transaction.getUser(), transaction.getTransactionDate().getMonthValue(), transaction.getTransactionDate().getYear())
                    .stream()
                    .map(Transaction::getAmount)
                    .filter(amount1 -> amount1 != null) // Ensure amounts are not null
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (totalSpent.add(amount).compareTo(limitAmount) > 0) {
                transaction.setLimitExceeded(true);
                logger.info("Transaction limit exceeded for user {}. Amount: {}", transaction.getUser(), amount);
            } else {
                transaction.setLimitExceeded(false);
            }
        } else {
            transaction.setLimitExceeded(false);
        }

        transactionRepository.save(transaction);
    }

    public List<Transaction> getExceededTransactions() {
        return transactionRepository.findByLimitExceededTrue();
    }
}
