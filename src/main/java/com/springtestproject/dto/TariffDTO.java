package com.springtestproject.dto;

import com.springtestproject.entity.Tariff;
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
    private String product;
    private String tariff;
    private BigDecimal price;

    public TariffDTO(Tariff tariff) {
        this.id = tariff.getId();
        this.product = tariff.getProduct().getProduct();
        this.tariff = tariff.getTariff();
        this.price = tariff.getPrice();
    }
}
