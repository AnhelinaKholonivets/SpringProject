package com.springtestproject.dto;

import com.springtestproject.entity.Order;
import com.springtestproject.entity.Tariff;
import com.springtestproject.entity.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {
    private Long id;
    private String userEmail;
    private TariffDTO tariff;
    //LocalDateTime
    private Date dateTime;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userEmail = order.getUser().getEmail();
        this.tariff = new TariffDTO(order.getTariff());
        this.dateTime = order.getDateTime();
    }
}