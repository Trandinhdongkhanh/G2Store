package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.SubCategory;
import com.hcmute.g2store.exception.CategoryException;
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
    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
        return subCategoryRepo.save(subCategory);
    }

    @Override
    @Transactional
    public SubCategory updateSubCategory(Integer id, String name) {
        Optional<SubCategory> subCategory = subCategoryRepo.findById(id);
        if (subCategory.isPresent()){
            subCategory.get().setName(name);
            return subCategory.get();
        }
        throw new CategoryException("SubCategory " + name + " not found");
    }

    @Override
    @Transactional
    public SubCategory delSubCategory(Integer id) {
        Optional<SubCategory> subCategory = subCategoryRepo.findById(id);
        if (subCategory.isPresent()){
            subCategory.get().setEnabled(false);
            return subCategory.get();
        }
        throw new CategoryException("SubCategory" + " not found");
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
