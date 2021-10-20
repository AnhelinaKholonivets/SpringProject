package com.springtestproject.controller;

import com.springtestproject.entity.Role;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller //(for users)
public class PageController {

    private final TariffService tariffService;
    private final OrderService orderService;
    private final UserService userService;

    public PageController(TariffService tariffService, OrderService orderService, UserService userService) {
        this.tariffService = tariffService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = {"/", "home"})
    public String mainPage(Model model) {
        model.addAttribute("text", "Some different text");
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/tariffs")
    public String getAllTariffs(Model model, Authentication authentication) {
        model.addAttribute("tariffs", tariffService.getAllTariffs().getTariffs());
        List<String> userRoles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (userRoles.contains(Role.ROLE_ADMIN.toString())) {
            return "tariff/allTariffsAdmin";
        }
        return "tariff/allTariffs";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model) {
       // model.addAttribute("orders", orderService.getAllOrdersByUser(userService.getCurrentUser()));
        model.addAttribute("user", userService.getCurrentUser());
        return "user/profile";
    }
}