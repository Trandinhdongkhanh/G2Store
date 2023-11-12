package com.hcmute.g2store.controller;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.entity.SubCategory;
import com.hcmute.g2store.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/api/v1/sub-categories")
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/api/v1/sub-categories/{cateId}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCateId(@PathVariable("cateId") Integer id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(id));
    }

    @PostMapping("/api/v1/add-sub-category")
    public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory subCategory) {
        return ResponseEntity.ok(subCategoryService.addSubCategory(subCategory));
    }

    @PutMapping("/api/v1/update-sub-category/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(
            @PathVariable("id") Integer id,
            @RequestBody SubCategory subCategory) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategory));
    }
    @PutMapping("/api/v1/del-sub-category/{id}")
    public ResponseEntity<SubCategory> delSubCategory(@PathVariable("id") Integer id){
        return ResponseEntity.ok(subCategoryService.delSubCategory(id));
    }
}
