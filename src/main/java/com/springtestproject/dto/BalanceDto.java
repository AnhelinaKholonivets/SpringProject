package com.springtestproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDto {
    @JsonProperty("addToBalance")
    private BigDecimal addToBalance;
}
