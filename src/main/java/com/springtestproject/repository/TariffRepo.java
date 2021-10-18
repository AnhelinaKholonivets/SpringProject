package com.springtestproject.repository;

import com.springtestproject.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepo extends JpaRepository<Tariff, Long> {
    Tariff findById(long id);
    }