package com.springtestproject.dto;

import com.springtestproject.entity.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrdersDTO {
    private List<Order> orders;
}