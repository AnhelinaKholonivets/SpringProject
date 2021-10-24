package com.springtestproject.service;

import com.springtestproject.dto.OrderDto;
import com.springtestproject.entity.User;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    List<OrderDto>  getAllOrdersByUser(User user);
    void saveNewOrders(List<Long> tariffsIds, Long userId);
}

