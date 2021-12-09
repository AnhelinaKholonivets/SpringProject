package com.springtestproject;

import com.springtestproject.entity.User;
import com.springtestproject.repository.UserRepo;
import com.springtestproject.service.UserService;
import com.springtestproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private final UserRepo userRepo = Mockito.mock(UserRepo.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    private final UserService userService = new UserServiceImpl(userRepo, passwordEncoder);

    @Test
    void updateBalanceWhenUserRefileBalance() {
        long id = 1;
        User user = createUserWithBalance(id, 10);
        int addBalance = 25;

        when(userRepo.findById(eq(id))).thenReturn(user);

        userService.refileBalance(id, BigDecimal.valueOf(addBalance));

        User expectUser = createUserWithBalance(id, 35);

        verify(userRepo).save(eq(expectUser));
    }

    @Test
    void blockUserWhereUserIsUnblocked() {
        long id = 1;
        User user = createUserWithBlockStatus(id, false);

        when(userRepo.findById(eq(id)))
                .thenReturn(user);

        userService.blockUser(user.getId());

        User expectUser = createUserWithBlockStatus(id, true);

        verify(userRepo).save(eq(expectUser));

    }

    private User createUserWithBalance(long id, double balance) {
        User user = new User();
        user.setId(id);
        user.setBalance(BigDecimal.valueOf(balance));

        return user;
    }

    private User createUserWithBlockStatus(long id, boolean block) {
        User user = new User();
        user.setId(id);
        user.setBlocked(block);

        return user;
    }
}
