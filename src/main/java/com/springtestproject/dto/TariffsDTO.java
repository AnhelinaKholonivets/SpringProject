package com.springtestproject.dto;

import com.springtestproject.entity.Tariff;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TariffsDTO {
    private List<Tariff> tariffs;
}
