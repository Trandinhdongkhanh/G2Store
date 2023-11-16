package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/v1/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/api/v1/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/api/v1/update-category")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

    @PutMapping("/api/v1/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
    @GetMapping("/api/v1/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
