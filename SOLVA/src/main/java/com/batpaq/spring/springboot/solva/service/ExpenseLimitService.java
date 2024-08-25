package com.batpaq.spring.springboot.solva.service;

import com.batpaq.spring.springboot.solva.entity.ExpenseLimit;
import com.batpaq.spring.springboot.solva.dao.ExpenseLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ExpenseLimitService {

    @Autowired
    private ExpenseLimitRepository expenseLimitRepository;

    public void setExpenseLimit(ExpenseLimit newLimit) {
        ExpenseLimit currentLimit = expenseLimitRepository.findTopByOrderByPeriodEndDesc();
        if (currentLimit == null || newLimit.getPeriodStart().isAfter(OffsetDateTime.now())) {
            expenseLimitRepository.save(newLimit);
        }
    }

    public ExpenseLimit getCurrentLimit() {
        return expenseLimitRepository.findTopByOrderByPeriodEndDesc();
    }
}