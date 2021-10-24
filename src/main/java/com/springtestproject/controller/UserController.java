package com.springtestproject.controller;

import com.springtestproject.dto.UserDto;
import com.springtestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers().getUsers());
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
