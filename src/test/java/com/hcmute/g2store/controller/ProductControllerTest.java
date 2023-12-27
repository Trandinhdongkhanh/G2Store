package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEnabledProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllEnabledProducts()).thenReturn(products);
        ResponseEntity<List<Product>> responseEntity = productController.getAllEnabledProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
        verify(productService, times(1)).getAllEnabledProducts();
    }

    @Test
    void getProductsByCategory() {
        int categoryId = 1;
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getProductsByCategory(categoryId)).thenReturn(products);
        ResponseEntity<List<Product>> responseEntity = productController.getProductsByCategory(categoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
        verify(productService, times(1)).getProductsByCategory(categoryId);
    }

    @Test
    void getProductById() {
        int productId = 1;
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(product);
        ResponseEntity<Product> responseEntity = productController.getProductById(productId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void getProductsByProviderId() {
        int providerId = 1;
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getProductsByProviderId(providerId)).thenReturn(products);
        ResponseEntity<List<Product>> responseEntity = productController.getProductsByProviderId(providerId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
        verify(productService, times(1)).getProductsByProviderId(providerId);
    }


    @Test
    void getAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void addProduct() {
        Product productToAdd = new Product();
        when(productService.addProduct(productToAdd)).thenReturn(productToAdd);
        ResponseEntity<Product> responseEntity = productController.addProduct(productToAdd);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productToAdd, responseEntity.getBody());
        verify(productService, times(1)).addProduct(productToAdd);
    }

    @Test
    void updateProduct() {
        Product productToUpdate = new Product();
        when(productService.updateProduct(productToUpdate)).thenReturn(productToUpdate);
        ResponseEntity<Product> responseEntity = productController.updateProduct(productToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productToUpdate, responseEntity.getBody());
        verify(productService, times(1)).updateProduct(productToUpdate);
    }

    @Test
    void delProduct() {
        int productId = 1;
        Product mockProduct = Product.builder()
                .id(productId)
                .name("ProductName")
                .build();
        when(productService.delProduct(productId)).thenReturn(mockProduct);
        ResponseEntity<Product> responseEntity = productController.delProduct(productId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(productService, times(1)).delProduct(productId);
    }
}

