package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.exception.CategoryException;
import com.hcmute.g2store.repository.CategoryRepo;
import com.hcmute.g2store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, String name) {
        Category category = categoryRepo.findById(id).get();
        if (category == null)  throw new CategoryException("");

    }

    @Override
    public Category deleteCategory(Integer id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            category.get().setEnabled(false);
            return category.get();
        }
        throw new CategoryException("Category with id" + id + " not found");
    }
}
