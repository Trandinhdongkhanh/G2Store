package com.hcmute.g2store.service;

import com.hcmute.g2store.entity.Category;

public interface CategoryService {
    Category addCategory(Category category);
    void updateCategory(Integer id, String name);
    Category deleteCategory(Integer id);
}
