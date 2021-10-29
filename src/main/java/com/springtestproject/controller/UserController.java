package com.springtestproject.controller;

import com.springtestproject.dto.UserDto;
import com.springtestproject.entity.User;
import com.springtestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model, @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<UserDto> users = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("users", users);

        int totalPages = users.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user/allUsers";
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "user/addUser";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute("user") UserDto user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return "Done";
    }
}
