package com.springtestproject.controller;

import com.springtestproject.dto.OrderDto;
import com.springtestproject.entity.Role;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final TariffService tariffService;

    public OrderController(OrderService orderService, UserService userService, TariffService tariffService) {
        this.orderService = orderService;
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @GetMapping
    public String getAllOrders(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        int totalPages = 0;

            Sort sort = Sort.by(Sort.Direction.DESC, "dateTime");

        String userRole = userService.getCurrentUser().getRole();

        Page<OrderDto> orders;

        if (userRole.equals(Role.ROLE_ADMIN.toString())) {
            orders = orderService.findPaginated(PageRequest.of(currentPage - 1, pageSize, sort), null);
        } else {
            orders = orderService.findPaginated(PageRequest.of(currentPage - 1, pageSize, sort),
                    userService.getCurrentUser().getId());
        }

        totalPages = orders.getTotalPages();
        model.addAttribute("orders", orders);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "order/allOrders";
    }

    @GetMapping("/new")
    public String addTariff(Model model) {
        model.addAttribute("tariffs", tariffService.findAllTariffs());
        return "order/newOrder";
    }

    @PostMapping("/new")
    @ResponseBody
    public List<OrderDto> addNewTariff(@RequestBody List<Long> tariffsIds) {
        Long userId = userService.getCurrentUser().getId();
        return orderService.saveNewOrders(tariffsIds, userId);
    }

}
