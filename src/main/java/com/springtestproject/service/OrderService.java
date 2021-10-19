package com.springtestproject.service;

import com.springtestproject.dto.OrdersDTO;
import com.springtestproject.entity.Order;

public interface OrderService {
    OrdersDTO getAllOrders();
    void saveNewOrder (Order order);
}
