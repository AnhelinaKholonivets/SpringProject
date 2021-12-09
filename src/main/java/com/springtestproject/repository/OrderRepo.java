package com.springtestproject.repository;


import com.springtestproject.entity.Order;
import com.springtestproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    Page<Order> findAllByUser(Pageable pageable, User user);
}
