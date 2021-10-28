package com.springtestproject.service;

import com.springtestproject.dto.UserDto;
import com.springtestproject.dto.UsersDto;
import com.springtestproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface UserService {
    UsersDto getAllUsers();
    User findByUserLogin(UserDto userDTO);
    void saveUser(UserDto user);
    void blockUser(long id);
    User getCurrentUser();
    void refileBalance(long id, BigDecimal addBalance);
    Page<User> findPaginated(Pageable pageable);
}
