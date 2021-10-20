package com.springtestproject.service.impl;

import com.springtestproject.dto.UserDTO;
import com.springtestproject.dto.UsersDTO;
import com.springtestproject.entity.Role;
import com.springtestproject.entity.User;
import com.springtestproject.repository.UserRepo;
import com.springtestproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepo.findAll());
    }

    @Override
    public User findByUserLogin(UserDTO userDTO) {
        //TODO check for user availability. password check
        //return userRepo.findByEmail(userDTO.getEmail());
        return userRepo.findUsersByEmail(userDTO.getEmail());
    }

    @Override
    public void saveUser(UserDTO user) {
        //TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            User userToSave = new User(null, user.getFirstName(), user.getLastName(),
                    user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getBalance(), false, Role.ROLE_USER.toString());
            userRepo.save(userToSave);
        } catch (Exception ex) {
            log.info("{Error save user}");
        }
    }

    @Override
    public void blockUser(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(!user.getBlocked());
            userRepo.save(user);
        }
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}