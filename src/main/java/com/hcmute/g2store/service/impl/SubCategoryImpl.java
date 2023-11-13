package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.Category;
import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.entity.SubCategory;
import com.hcmute.g2store.exception.CategoryException;
import com.hcmute.g2store.exception.ProductException;
import com.hcmute.g2store.repository.CategoryRepo;
import com.hcmute.g2store.repository.SubCategoryRepo;
import com.hcmute.g2store.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
        Integer cateId = subCategory.getCategory().getId();
        Optional<Category> category = categoryRepo.findById(cateId);
        if (category.isEmpty()) throw new CategoryException("Category " + cateId + " not found");
        subCategory.setCategory(category.get());
        return subCategoryRepo.save(subCategory);
    }

    @Override
    @Transactional
    public SubCategory updateSubCategory(Integer subCateId, String name, Integer cateId) {
        Optional<SubCategory> subCategory = subCategoryRepo.findById(subCateId);
        Optional<Category> category = categoryRepo.findById(cateId);
        if (subCategory.isEmpty()) throw new CategoryException("SubCategory " + subCateId +" not found");
        if (category.isEmpty()) throw new CategoryException("Category " + cateId + " not found");
        subCategory.get().setName(name);
        subCategory.get().setCategory(category.get());
        return subCategory.get();
    }

    @Override
    @Transactional
    public SubCategory delSubCategory(Integer id) {
        Optional<SubCategory> subCategory = subCategoryRepo.findById(id);
        if (subCategory.isPresent()) {
            subCategory.get().setEnabled(false);
            return subCategory.get();
        }
        throw new CategoryException("SubCategory" + " not found");
    }

    @Override
    public SubCategory getSubCategoryById(Integer id) {
        Optional<SubCategory> subCategory = subCategoryRepo.findById(id);
        if (subCategory.isPresent()){
            return subCategory.get();
        }
        throw new CategoryException("SubCategory " + id + " not found");
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepo.findAll();
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategoryId(Integer id) {
        return subCategoryRepo.findAllByCategory(id);
    }
}
