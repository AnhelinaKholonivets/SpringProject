package com.springtestproject.service;

import com.springtestproject.dto.OrdersDTO;
import com.springtestproject.entity.Order;
import com.springtestproject.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public OrdersDTO getAllOrders() {
        //TODO checking for an empty user list
        return new OrdersDTO(orderRepo.findAll());
    }

    public void saveNewOrder (Order order){
        try {
            orderRepo.save(order);
        } catch (Exception ex){
            log.info("{Order was in table}");
        }

    }

}
