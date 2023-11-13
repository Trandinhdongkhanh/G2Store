package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.entity.Provider;
import com.hcmute.g2store.entity.SubCategory;
import com.hcmute.g2store.exception.CategoryException;
import com.hcmute.g2store.exception.ProductException;
import com.hcmute.g2store.exception.ProviderException;
import com.hcmute.g2store.repository.ProductRepo;
import com.hcmute.g2store.repository.ProviderRepo;
import com.hcmute.g2store.repository.SubCategoryRepo;
import com.hcmute.g2store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private ProviderRepo providerRepo;
    @Override
    public Product addProduct(Product product) {
        Integer subCateId = product.getSubCategory().getId();
        Integer providerId = product.getProvider().getId();
        Optional<SubCategory> subCategory = subCategoryRepo.findById(subCateId);
        Optional<Provider> provider = providerRepo.findById(providerId);

        if (subCategory.isEmpty()) {
            throw new CategoryException("SubCategory " + subCateId + " not found");
        }
        if (provider.isEmpty()) {
            throw new ProviderException("Provider " + providerId + " not found");
        }

        product.setSubCategory(subCategory.get());
        product.setProvider(provider.get());

        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Integer subCateId = updatedProduct.getSubCategory().getId();
        Integer providerId = updatedProduct.getProvider().getId();
        Optional<SubCategory> subCategory = subCategoryRepo.findById(subCateId);
        Optional<Provider> provider = providerRepo.findById(providerId);
        Optional<Product> product = productRepo.findById(updatedProduct.getId());
        if (subCategory.isEmpty()) {
            throw new CategoryException("SubCategory " + subCateId + " not found");
        }
        if (provider.isEmpty()) {
            throw new ProviderException("Provider " + providerId + " not found");
        }
        if (product.isEmpty()) {
            throw new ProductException("Product with ID " + updatedProduct.getId() + " not found");
        }
        Product productToUpdate = product.get();
        productToUpdate.setSubCategory(subCategory.get());
        productToUpdate.setProvider(provider.get());
        productToUpdate.setName(updatedProduct.getName());
        productToUpdate.setPrice(updatedProduct.getPrice());
        productToUpdate.setDescription(updatedProduct.getDescription());
        productToUpdate.setDiscount(updatedProduct.getDiscount());
        productToUpdate.setImage(updatedProduct.getImage());
        return productRepo.save(productToUpdate);
    }

    @Override
    public Product delProduct(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            product.get().setEnabled(false);
            return product.get();
        }
        throw new ProductException("Product" + product.get().getName() + " not found");
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        throw new ProductException("Product " + id + " not found");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsBySubCategoryId(Integer id) {
        return productRepo.findAllBySubCategory(id);
    }

    @Override
    public List<Product> getProductsByProviderId(Integer id) {
        return productRepo.findAllByProvider(id);
    }
}
