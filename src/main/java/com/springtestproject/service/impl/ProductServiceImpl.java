package com.springtestproject.service.impl;

import com.springtestproject.dto.ProductsDTO;
import com.springtestproject.entity.Product;
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
    public ProductsDTO getAllProducts() {
        //TODO checking for an empty user list
        return new ProductsDTO(productRepo.findAll());
    }

    public void saveNewProduct(Product product){
        try {
            productRepo.save(product);
        } catch (Exception ex){
            log.info("{Order was in table}");
        }
    }
}
