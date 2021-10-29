package com.springtestproject.controller;

import com.springtestproject.exception.LowBalanceException;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
