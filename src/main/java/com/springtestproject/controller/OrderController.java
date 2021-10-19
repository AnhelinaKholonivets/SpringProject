package com.springtestproject.controller;

import com.springtestproject.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders().getOrders());
        return "allOrdersAdmin";
    }
}
