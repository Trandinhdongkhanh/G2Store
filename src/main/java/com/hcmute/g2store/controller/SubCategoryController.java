package com.hcmute.g2store.controller;

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

    @GetMapping("/api/v1/sub-categories/{id}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCateId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(id));
    }

    @PostMapping("/api/v1/add-sub-category")
    public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory subCategory) {
        return ResponseEntity.ok(subCategoryService.addSubCategory(subCategory));
    }

    @PutMapping("/api/v1/update-sub-category/{subCateId}")
    public ResponseEntity<SubCategory> updateSubCategory(
            @PathVariable("subCateId") Integer subCateId,
            @RequestParam("name") String name,
            @RequestParam(value = "cateId", required = false) Integer cateId) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(subCateId, name, cateId));
    }
    @PutMapping("/api/v1/del-sub-category/{id}")
    public ResponseEntity<SubCategory> delSubCategory(@PathVariable("id") Integer id){
        return ResponseEntity.ok(subCategoryService.delSubCategory(id));
    }
}
