package com.springtestproject.service.impl;

import com.springtestproject.dto.ProductsDto;
import com.springtestproject.repository.ProductRepo;
import com.springtestproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ProductsDto findAllProducts() {
        //TODO checking for an empty user list
        return new ProductsDto(productRepo.findAll());
    }
}
