package com.batpaq.spring.springboot.solva.entity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
@Entity
@Table(name = "usert")
public class User {

    @Id
    @SequenceGenerator(name = "solva_1", sequenceName = "solva_1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solva_1")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;


        public User(String email, OffsetDateTime createdAt, String username) {
                this.email = email;
                this.createdAt = createdAt;
                this.username = username;
        }

        public User() {

        }
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public OffsetDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(OffsetDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }
}
