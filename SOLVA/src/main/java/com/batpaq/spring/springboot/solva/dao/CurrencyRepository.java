package com.batpaq.spring.springboot.solva.dao;

import com.batpaq.spring.springboot.solva.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
