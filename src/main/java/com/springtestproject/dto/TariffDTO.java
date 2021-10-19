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
    public long id;
    public  String tariff;
    private BigDecimal price;
}
