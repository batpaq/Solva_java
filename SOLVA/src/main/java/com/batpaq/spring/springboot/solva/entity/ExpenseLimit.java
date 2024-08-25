package com.batpaq.spring.springboot.solva.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "expense_limits")
public class ExpenseLimit {

    @Id
    @SequenceGenerator(name = "solva_4", sequenceName = "solva_4", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solva_4")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "limit_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal limitAmount;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "period_start", nullable = false)
    private OffsetDateTime periodStart;

    @Column(name = "period_end", nullable = false)
    private OffsetDateTime periodEnd;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    public ExpenseLimit(OffsetDateTime createdAt, Currency currency, BigDecimal limitAmount, OffsetDateTime periodEnd, OffsetDateTime periodStart, User user) {
        this.createdAt = createdAt;
        this.currency = currency;
        this.limitAmount = limitAmount;
        this.periodEnd = periodEnd;
        this.periodStart = periodStart;
        this.user = user;
    }

    public ExpenseLimit() {

    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public OffsetDateTime getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(OffsetDateTime periodEnd) {
        this.periodEnd = periodEnd;
    }

    public OffsetDateTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(OffsetDateTime periodStart) {
        this.periodStart = periodStart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLimitSum(BigDecimal limitAmount) {

    }
}
