package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.repository.ProductRepo;
import com.hcmute.g2store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
}
