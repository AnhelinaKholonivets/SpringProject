package com.springtestproject.service;

import com.springtestproject.dto.ProductsDto;
import com.springtestproject.entity.Product;

public interface ProductService {
    ProductsDto getAllProducts();
    void saveNewProduct(Product product);
}
