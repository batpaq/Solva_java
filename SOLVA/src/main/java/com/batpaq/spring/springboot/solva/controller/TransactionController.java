package com.batpaq.spring.springboot.solva.controller;

import com.batpaq.spring.springboot.solva.entity.Transaction;
import com.batpaq.spring.springboot.solva.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
    }

    @GetMapping("/exceeded")
    public List<Transaction> getExceededTransactions() {
        return transactionService.getExceededTransactions();
    }
}