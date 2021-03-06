package com.springtestproject.controller;

import com.springtestproject.dto.BalanceDto;
import com.springtestproject.dto.TariffDto;
import com.springtestproject.entity.Role;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
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
    public String getAllTariffs(Model model, Authentication authentication,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                @RequestParam("sortField") Optional<String> sortField,
                                @RequestParam("sortDir") Optional<String> sortDir) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Sort sort = Sort.unsorted();

        if (sortField.isPresent()) {
            String sortDirection = sortDir.orElse("asc");
           sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField.get());
        }

        Page<TariffDto> tariffs = tariffService.findPaginated(PageRequest.of(currentPage - 1, pageSize, sort));

        model.addAttribute("reverseSortDir", "asc".equals(sortDir.orElse(null)) ? "desc" : "asc");
        model.addAttribute("tariffs", tariffs);

        int totalPages = tariffs.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageNumbers", pageNumbers);
        }

        String userRole = userService.getCurrentUser().getRole();
        if (userRole.equals(Role.ROLE_ADMIN.toString())) {
            return "tariff/allTariffsAdmin";
        }
        return "tariff/allTariffs";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("orders", orderService.findAllOrdersByUser(userService.getCurrentUser()));
        return "user/profile";
    }

    @PutMapping("/user/profile")
    @ResponseBody
    public BalanceDto updateBalance(BalanceDto balanceDto) {
        Long id = userService.getCurrentUser().getId();
        userService.refileBalance(id, balanceDto.getAddToBalance());
        return balanceDto;
    }
}