package com.batpaq.spring.springboot.solva.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @SequenceGenerator(name = "solva_2", sequenceName = "solva_2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solva_2")
    private Long id;

    @Column(nullable = false, unique = true, length = 3)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "exchange_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal exchangeRate;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    public Currency(String code, OffsetDateTime createdAt, BigDecimal exchangeRate, String name) {
        this.code = code;
        this.createdAt = createdAt;
        this.exchangeRate = exchangeRate;
        this.name = name;
    }

    public Currency() {

    }
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}