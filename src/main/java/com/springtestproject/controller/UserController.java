package com.springtestproject.controller;

import com.springtestproject.dto.UserDTO;
import com.springtestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
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
    public String addNewUser(@ModelAttribute("user") UserDTO user) {
        userService.saveUser(user);
        return "redirect:/allUsers";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String blockUser(@PathVariable Long id) {
        //userService.saveUser();
        return "Deleted";
    }
}
