package com.springtestproject.service;

import com.springtestproject.dto.UserDTO;
import com.springtestproject.dto.UsersDTO;
import com.springtestproject.entity.User;
import com.springtestproject.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepo.findAll());
    }

    public Optional<User> findByUserLogin (UserDTO userDTO){
        //TODO check for user availability. password check
        return userRepo.findByEmail(userDTO.getEmail());
    }

    public void saveNewUser (User user){
        //TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            userRepo.save(user);
        } catch (Exception ex){
            log.info("{Error save user}");
        }

    }

}