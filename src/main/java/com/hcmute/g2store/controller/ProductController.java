package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.entity.Provider;
import com.hcmute.g2store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/api/v1/products-enabled")
    public ResponseEntity<List<Product>> getAllEnabledProducts() {
        return ResponseEntity.ok(productService.getAllEnabledProducts());
    }
    @GetMapping("/api/v1/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/api/v1/product-provider/{id}")
    public ResponseEntity<List<Product>> getProductsByProviderId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProductsByProviderId(id));
    }
    @GetMapping("/api/v1/product-subcategory/{id}")
    public ResponseEntity<List<Product>> getProductsBySubCategoryId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProductsBySubCategoryId(id));
    }
    @PostMapping("/api/v1/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/api/v1/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }
    @PutMapping("/api/v1/del-product/{id}")
    public ResponseEntity<Product> delProduct(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.delProduct(id));
    }
}
