package com.springtestproject.controller;

import com.springtestproject.entity.User;
import com.springtestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers().getUsers());
        return "allUsers";
    }

    @GetMapping("/users/addUser")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/users/addUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        User userToSave = new User(null, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(), user.getBalance(), true,
                user.getRoles());
        userService.saveUser(userToSave);
        return "redirect:/allUsers";
    }

}
