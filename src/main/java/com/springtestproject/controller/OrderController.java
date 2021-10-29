package com.springtestproject.controller;

import com.springtestproject.entity.Role;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final TariffService tariffService;

    public OrderController(OrderService orderService, UserService userService, TariffService tariffService) {
        this.orderService = orderService;
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {

        String userRole = userService.getCurrentUser().getRole().toString();

        if (userRole.contains(Role.ROLE_ADMIN.toString())) {
            model.addAttribute("orders", orderService.getAllOrders());
        } else model.addAttribute("orders", orderService.getAllOrdersByUser(userService.getCurrentUser()));

        return "order/allOrders";
    }

    @GetMapping("/orders/new")
    public String addTariff(Model model) {
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "order/newOrder";
    }

    @PostMapping("/orders/new")
    public String addNewTariff(@RequestBody List<Long> tariffsIds) {
        Long userId = userService.getCurrentUser().getId();
        orderService.saveNewOrders(tariffsIds, userId);
        return "redirect:/profile";
    }

}
