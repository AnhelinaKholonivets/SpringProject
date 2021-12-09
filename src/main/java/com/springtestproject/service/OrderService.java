package com.springtestproject.service;

import com.springtestproject.dto.OrderDto;
import com.springtestproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllOrdersByUser(User user);

    List<OrderDto> saveNewOrders(List<Long> tariffsIds, long userId);

    Page<OrderDto> findPaginated(Pageable pageable, Long userId);
}

