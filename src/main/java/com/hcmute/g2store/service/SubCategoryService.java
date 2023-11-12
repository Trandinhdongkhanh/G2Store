package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    SubCategory addSubCategory(SubCategory subCategory);
    SubCategory updateSubCategory(Integer id, String name);
    SubCategory delSubCategory(Integer id);
    List<SubCategory> getAllSubCategories();
    List<SubCategory> getSubCategoriesByCategoryId(Integer id);
}
