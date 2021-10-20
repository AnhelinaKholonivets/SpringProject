package com.springtestproject.service;

import com.springtestproject.dto.UserDTO;
import com.springtestproject.dto.UsersDTO;
import com.springtestproject.entity.User;

public interface UserService {
    UsersDTO getAllUsers();
    User findByUserLogin(UserDTO userDTO);
    void saveUser(UserDTO user);
    void blockUser(Long id);
    User getCurrentUser();
}
