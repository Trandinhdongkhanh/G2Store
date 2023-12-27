package com.hcmute.g2store.controller;
import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.entity.SubCategory;
import com.hcmute.g2store.service.SubCategoryService;
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

public class SubCategoryControllerTest {

    @Mock
    private SubCategoryService subCategoryService;

    @InjectMocks
    private SubCategoryController subCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEnabledSubCategories() {
        List<SubCategory> subCategories = Arrays.asList(new SubCategory(), new SubCategory());
        when(subCategoryService.getAllEnabledSubCategories()).thenReturn(subCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllEnabledSubCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategories, responseEntity.getBody());
        verify(subCategoryService, times(1)).getAllEnabledSubCategories();
    }

    @Test
    void getSubCategoryById() {
        int subCategoryId = 1;
        SubCategory subCategory = new SubCategory();
        when(subCategoryService.getSubCategoryById(subCategoryId)).thenReturn(subCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.getSubCategoryById(subCategoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategory, responseEntity.getBody());
        verify(subCategoryService, times(1)).getSubCategoryById(subCategoryId);
    }

    @Test
    void getSubCategoriesByCateId() {
        int categoryId = 1;
        List<SubCategory> subCategories = Arrays.asList(new SubCategory(), new SubCategory());
        when(subCategoryService.getSubCategoriesByCategoryId(categoryId)).thenReturn(subCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getSubCategoriesByCateId(categoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategories, responseEntity.getBody());
        verify(subCategoryService, times(1)).getSubCategoriesByCategoryId(categoryId);
    }

    @Test
    void getAllSubCategories() {
        List<SubCategory> subCategories = Arrays.asList(new SubCategory(), new SubCategory());
        when(subCategoryService.getAllSubCategories()).thenReturn(subCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllSubCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategories, responseEntity.getBody());
        verify(subCategoryService, times(1)).getAllSubCategories();
    }

    @Test
    void addSubCategory() {
        SubCategory subCategoryToAdd = new SubCategory();
        when(subCategoryService.addSubCategory(subCategoryToAdd)).thenReturn(subCategoryToAdd);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.addSubCategory(subCategoryToAdd);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategoryToAdd, responseEntity.getBody());
        verify(subCategoryService, times(1)).addSubCategory(subCategoryToAdd);
    }
    @Test
    void updateCategory() {
        SubCategory subCategoryToUpdate = new SubCategory();
        when(subCategoryService.updateSubCategory(subCategoryToUpdate)).thenReturn(subCategoryToUpdate);
        ResponseEntity<?> responseEntity = subCategoryController.updateSubCategory(subCategoryToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(subCategoryService, times(1)).updateSubCategory(subCategoryToUpdate);
    }

    @Test
    void deleteCategory() {
        int subCategoryId = 1;
        SubCategory mockSubCategory = SubCategory.builder()
                .id(subCategoryId)
                .name("CategoryName")
                .build();
        when(subCategoryService.delSubCategory(subCategoryId)).thenReturn(mockSubCategory);
        ResponseEntity<?> responseEntity = subCategoryController.delSubCategory(subCategoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(subCategoryService, times(1)).delSubCategory(subCategoryId);
    }
}