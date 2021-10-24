package com.springtestproject.dto;

import com.springtestproject.entity.Order;
import lombok.*;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String userEmail;
    private TariffDto tariff;
    private LocalDateTime dateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.userEmail = order.getUser().getEmail();
        this.tariff = new TariffDto(order.getTariff());
        this.dateTime = order.getDateTime();
    }
}