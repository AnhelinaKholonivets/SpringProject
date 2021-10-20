package com.springtestproject.service.impl;

import com.springtestproject.dto.OrderDTO;
import com.springtestproject.entity.Order;
import com.springtestproject.entity.User;
import com.springtestproject.repository.OrderRepo;
import com.springtestproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO>  getAllOrdersByUser(User user) {
        return orderRepo.findAllByUser(user)
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveNewOrder (Order order){
        try {
            orderRepo.save(order);
        } catch (Exception ex){
            log.info("{Order was in table}");
        }
    }

}
