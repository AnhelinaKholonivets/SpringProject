package com.springtestproject.dto;

import com.springtestproject.entity.User;
import lombok.*;

import java.math.BigDecimal;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BigDecimal balance;
    private Boolean blocked;
    private String role;
    public UserDto(User user) {
    }
}
