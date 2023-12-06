package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    Product delProduct(Integer id);
    Product getProductById(Integer id);
    List<Product> getAllProducts();
    List<Product> getAllEnabledProducts();
    List<Product> getProductsByCategory(Integer id);
    List<Product> getProductsBySubCategoryId(Integer id);
    List<Product> getProductsByProviderId(Integer id);
}
