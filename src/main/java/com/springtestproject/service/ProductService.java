package com.springtestproject.service;

import com.springtestproject.dto.ProductsDTO;
import com.springtestproject.entity.Product;

public interface ProductService {
    ProductsDTO getAllProducts();
    void saveNewProduct(Product product);

}
