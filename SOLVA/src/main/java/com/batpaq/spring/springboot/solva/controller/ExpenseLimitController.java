package com.batpaq.spring.springboot.solva.controller;

import com.batpaq.spring.springboot.solva.entity.ExpenseLimit;
import com.batpaq.spring.springboot.solva.service.ExpenseLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/limits")
public class ExpenseLimitController {

    @Autowired
    private ExpenseLimitService expenseLimitService;

    @PostMapping
    public void setExpenseLimit(@RequestBody ExpenseLimit limit) {
        expenseLimitService.setExpenseLimit(limit);
    }

    @GetMapping("/get")
    public ExpenseLimit getCurrentLimit() {
        return expenseLimitService.getCurrentLimit();
    }
}