package com.springtestproject.service.impl;

import com.springtestproject.dto.UserDto;
import com.springtestproject.dto.UsersDto;
import com.springtestproject.entity.Role;
import com.springtestproject.entity.User;
import com.springtestproject.repository.UserRepo;
import com.springtestproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersDto getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDto(userRepo.findAll());
    }

    @Override
    public User findByUserLogin(UserDto userDTO) {
        //TODO check for user availability. password check
        //return userRepo.findByEmail(userDTO.getEmail());
        return userRepo.findUsersByEmail(userDTO.getEmail());
    }

    @Override
    public void saveUser(UserDto user) {
        //TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            User userToSave = new User(null, user.getFirstName(), user.getLastName(),
                    user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getBalance(),
                    false, Role.ROLE_USER.toString());
            userRepo.save(userToSave);
        } catch (Exception ex) {
            log.info("{Error save user}");
        }
    }

    @Override
    public void blockUser(long id) {
        User user = userRepo.findById(id);
        user.setBlocked(!user.getBlocked());
        userRepo.save(user);

    }

    @Override
    public User getCurrentUser() {
        User userInSpring = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findById(userInSpring.getId()).get();
    }

    @Override
    public void refileBalance(long id, BigDecimal addBalance) {
        User user = userRepo.findById(id);
        user.setBalance(user.getBalance().add(addBalance));
        userRepo.save(user);

    }

}