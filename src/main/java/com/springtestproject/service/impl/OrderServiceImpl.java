package com.springtestproject.service.impl;

import com.springtestproject.dto.OrderDto;
import com.springtestproject.entity.Order;
import com.springtestproject.entity.Tariff;
import com.springtestproject.entity.User;
import com.springtestproject.repository.OrderRepo;
import com.springtestproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto>  getAllOrdersByUser(User user) {
        return orderRepo.findAllByUser(user)
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveNewOrders (List<Long> tariffsIds, Long userId){

        User user = new User(userId);

        List<Tariff> tariffs = tariffsIds
                .stream()
                .map(id -> new Tariff( id, null, null, null))
                .collect(Collectors.toList());

        List<Order> orders = tariffs
                .stream()
                .map(tariff -> new Order(null, user, tariff, LocalDateTime.now()))
                .collect(Collectors.toList());

        try {
            orderRepo.saveAll(orders);
        } catch (Exception ex){
            log.info("{Orders not were saved}");
        }
    }

}
