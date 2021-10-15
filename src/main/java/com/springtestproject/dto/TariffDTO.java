package com.springtestproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TariffDTO {
    public  String name;
    private Double price;
    private Data date;
}
