package com.batpaq.spring.springboot.solva.service;

import com.batpaq.spring.springboot.solva.entity.Currency;
import com.batpaq.spring.springboot.solva.entity.Transaction;
import com.batpaq.spring.springboot.solva.entity.ExpenseLimit;
import com.batpaq.spring.springboot.solva.dao.TransactionRepository;
import com.batpaq.spring.springboot.solva.dao.ExpenseLimitRepository;
import com.batpaq.spring.springboot.solva.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExpenseLimitRepository expenseLimitRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction_WhenLimitExceeded_ShouldSetLimitExceededFlag() {
        // Arrange
        Transaction transaction = createTestTransaction(new BigDecimal("600"));
        ExpenseLimit limit = createTestExpenseLimit(new BigDecimal("1000"));

        when(expenseLimitRepository.findTopByOrderByPeriodEndDesc()).thenReturn(limit);
        when(transactionRepository.findByUserAndMonth(transaction.getUser(), transaction.getTransactionDate().getMonthValue(), transaction.getTransactionDate().getYear()))
                .thenReturn(Collections.singletonList(transaction));

        // Act
        transactionService.saveTransaction(transaction);

        // Assert
        assertThat(transaction.getLimitExceeded()).isTrue();
    }

    @Test
    void testSaveTransaction_WhenLimitNotExceeded_ShouldNotSetLimitExceededFlag() {
        // Arrange
        Transaction transaction = createTestTransaction(new BigDecimal("500"));
        ExpenseLimit limit = createTestExpenseLimit(new BigDecimal("1000"));

        when(expenseLimitRepository.findTopByOrderByPeriodEndDesc()).thenReturn(limit);
        when(transactionRepository.findByUserAndMonth(transaction.getUser(), transaction.getTransactionDate().getMonthValue(), transaction.getTransactionDate().getYear()))
                .thenReturn(Collections.singletonList(transaction));

        // Act
        transactionService.saveTransaction(transaction);

        // Assert
        assertThat(transaction.getLimitExceeded()).isFalse();
    }

    private Transaction createTestTransaction(BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("product");
        transaction.setTransactionDate(OffsetDateTime.now());
        transaction.setUser(new User());  // Create and initialize a test user if needed
        transaction.setCurrency(new Currency());  // Create and initialize a test currency if needed
        return transaction;
    }

    private ExpenseLimit createTestExpenseLimit(BigDecimal limitAmount) {
        ExpenseLimit limit = new ExpenseLimit();
        limit.setLimitAmount(limitAmount);  // Ensure this matches the field in ExpenseLimit
        limit.setPeriodStart(OffsetDateTime.now().minusDays(10));
        limit.setPeriodEnd(OffsetDateTime.now());
        limit.setCurrency(new Currency("USD", OffsetDateTime.now(), new BigDecimal("1.0"), "US Dollar"));
        return limit;
    }
}
