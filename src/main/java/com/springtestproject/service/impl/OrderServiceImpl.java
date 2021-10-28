package com.springtestproject.service.impl;

import com.springtestproject.dto.OrderDto;
import com.springtestproject.entity.Order;
import com.springtestproject.entity.Tariff;
import com.springtestproject.entity.User;
import com.springtestproject.repository.OrderRepo;
import com.springtestproject.repository.TariffRepo;
import com.springtestproject.repository.UserRepo;
import com.springtestproject.service.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final TariffRepo tariffRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserRepo userRepo, TariffRepo tariffRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.tariffRepo = tariffRepo;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrdersByUser(User user) {
        return orderRepo.findAllByUser(user)
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    @Transactional
    public void saveNewOrders(List<Long> tariffsIds, long userId) {

        List<Tariff> tariffs = tariffRepo.findAllById(tariffsIds);
        BigDecimal orderPrice = tariffs.stream()
                .map(Tariff::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        User user = userRepo.findById(userId);

        if (user.getBalance().compareTo(orderPrice) < 0) {
             throw new Exception("Error not enough money on user balance");
        }
        user.setBalance(user.getBalance().subtract(orderPrice));
        List<Order> orders = tariffs
                .stream()
                .map(tariff -> new Order(null, user, tariff, LocalDateTime.now()))
                .collect(Collectors.toList());
        try {
            userRepo.save(user);
            orderRepo.saveAll(orders);
        } catch (Exception ex) {
            log.info("{Orders not were saved}");
        }
    }
}
