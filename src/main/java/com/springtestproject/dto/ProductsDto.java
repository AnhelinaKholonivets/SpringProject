package com.springtestproject.dto;

import com.springtestproject.entity.Product;
import lombok.*;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private List<Product> products;
}