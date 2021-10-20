package com.springtestproject.controller;

import com.springtestproject.service.OrderService;
import com.springtestproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/allOrdersAdmin";
    }

    @GetMapping("/myorders")
    public String getAllOrdersForUser(Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByUser(userService.getCurrentUser()));
        return "order/allOrders";
    }
}
