package com.springtestproject.service;

import com.springtestproject.dto.UserDto;
import com.springtestproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface UserService {
    void saveUser(UserDto user);

    void blockUser(long id);

    User getCurrentUser();

    void refileBalance(long id, BigDecimal addBalance);

    Page<UserDto> findPaginated(Pageable pageable);
}
