package com.springtestproject.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TariffDTO {
    public long id;
    public  String name;
    private BigDecimal price;
}
