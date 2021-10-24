package com.springtestproject.dto;

import com.springtestproject.entity.Tariff;
import lombok.*;

import java.math.BigDecimal;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class TariffDto {
    private Long id;
    private String product;
    private String tariff;
    private BigDecimal price;

    public TariffDto(Tariff tariff) {
        this.id = tariff.getId();
        this.product = tariff.getProduct().getProduct();
        this.tariff = tariff.getTariff();
        this.price = tariff.getPrice();
    }
}
