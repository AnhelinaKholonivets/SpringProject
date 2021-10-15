package com.springtestproject.repository;

import com.springtestproject.entity.Tariff;
import com.springtestproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
    
}