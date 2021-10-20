package com.springtestproject.service;

import com.springtestproject.dto.OrderDTO;
import com.springtestproject.entity.Order;
import com.springtestproject.entity.User;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    List<OrderDTO>  getAllOrdersByUser(User user);
    void saveNewOrder (Order order);
}

