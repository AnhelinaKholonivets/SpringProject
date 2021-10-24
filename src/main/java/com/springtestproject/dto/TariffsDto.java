package com.springtestproject.dto;

import com.springtestproject.entity.Tariff;
import lombok.*;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class TariffsDto {
    private List<Tariff> tariffs;
}
