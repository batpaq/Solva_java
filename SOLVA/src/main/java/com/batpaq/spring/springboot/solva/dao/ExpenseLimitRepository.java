package com.batpaq.spring.springboot.solva.dao;

import com.batpaq.spring.springboot.solva.entity.ExpenseLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseLimitRepository extends JpaRepository<ExpenseLimit, Long> {

    ExpenseLimit findTopByOrderByPeriodEndDesc();
}
