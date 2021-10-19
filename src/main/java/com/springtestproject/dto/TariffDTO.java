package com.springtestproject.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TariffDTO {
    private Long id;
    private Long product;
    private String tariff;
    private BigDecimal price;
}
