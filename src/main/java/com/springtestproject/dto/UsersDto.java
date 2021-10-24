package com.springtestproject.dto;

import com.springtestproject.entity.User;
import lombok.*;

import java.util.List;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private List<User> users;
}