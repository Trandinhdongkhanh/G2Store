package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Integer id, String name);
    Category deleteCategory(Integer id);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
}
