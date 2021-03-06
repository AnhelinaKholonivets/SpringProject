package com.springtestproject.repository;

import com.springtestproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUsersByEmail(String email);

    User findById(long id);
}