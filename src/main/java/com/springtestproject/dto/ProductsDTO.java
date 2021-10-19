package com.springtestproject.dto;

import com.springtestproject.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductsDTO {
    private List<Product> products;
}