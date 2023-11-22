package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.service.CategoryService;
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

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Category 1"),
                new Category("Category 2")
        );
        when(categoryService.getAllCategories()).thenReturn(categories);
        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categories, responseEntity.getBody());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void addCategory() {
        Category categoryToAdd = new Category("New Category");
        when(categoryService.addCategory(categoryToAdd)).thenReturn(categoryToAdd);
        ResponseEntity<Category> responseEntity = categoryController.addCategory(categoryToAdd);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryToAdd, responseEntity.getBody());
        verify(categoryService, times(1)).addCategory(categoryToAdd);
    }

    @Test
    void updateCategory() {
        Category categoryToUpdate = new Category("Updated Category");
        when(categoryService.updateCategory(categoryToUpdate)).thenReturn(categoryToUpdate);

        ResponseEntity<?> responseEntity = categoryController.updateCategory(categoryToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(categoryService, times(1)).updateCategory(categoryToUpdate);
    }

    @Test
    void deleteCategory() {
        // Arrange
        int categoryId = 1;
        Category mockCategory = Category.builder()
                .id(categoryId)
                .name("CategoryName")
                .build();
        when(categoryService.deleteCategory(categoryId)).thenReturn(mockCategory);
        ResponseEntity<?> responseEntity = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    void getCategoryById() {
        int categoryId = 1;
        Category category = new Category("Category 1");
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        ResponseEntity<Category> responseEntity = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(category, responseEntity.getBody());
        verify(categoryService, times(1)).getCategoryById(categoryId);
    }
}
