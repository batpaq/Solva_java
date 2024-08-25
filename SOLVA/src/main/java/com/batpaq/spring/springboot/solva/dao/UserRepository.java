package com.batpaq.spring.springboot.solva.dao;

import com.batpaq.spring.springboot.solva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
