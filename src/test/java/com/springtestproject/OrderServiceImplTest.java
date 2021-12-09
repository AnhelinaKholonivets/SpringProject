package com.springtestproject;

import com.springtestproject.entity.Tariff;
import com.springtestproject.entity.User;
import com.springtestproject.exception.LowBalanceException;
import com.springtestproject.repository.OrderRepo;
import com.springtestproject.repository.TariffRepo;
import com.springtestproject.repository.UserRepo;
import com.springtestproject.service.OrderService;
import com.springtestproject.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


class OrderServiceImplTest {
    private final TariffRepo tariffRepo = Mockito.mock(TariffRepo.class);
    private final UserRepo userRepo = Mockito.mock(UserRepo.class);
    private final OrderRepo orderRepo = Mockito.mock(OrderRepo.class);

    private final OrderService orderService = new OrderServiceImpl(orderRepo, userRepo, tariffRepo);


    @Test
    void saveNewOrdersShouldThrowLowBalanceExceptionWhenUserBalanceIsLowerThanSumOfTariffsPrice() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        long userId = 1;
        int balance = 2;

        when(tariffRepo.findAllById(eq(ids)))
                .thenReturn(findAllByIdsResponse(3, 4));

        when(userRepo.findById(eq(userId)))
                .thenReturn(createUserWithBalance(balance));

        LowBalanceException ex = assertThrows(LowBalanceException.class,
                () -> orderService.saveNewOrders(ids, userId));

        assertNotNull(ex);
    }

    private List<Tariff> findAllByIdsResponse(double... prices) {
        List<Tariff> result = new ArrayList<>();
        for (double price : prices) {
            Tariff tariff = new Tariff();
            tariff.setPrice(BigDecimal.valueOf(price));
            result.add(tariff);
        }

        return result;
    }

    private User createUserWithBalance(double balance) {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(balance));

        return user;
    }
}
