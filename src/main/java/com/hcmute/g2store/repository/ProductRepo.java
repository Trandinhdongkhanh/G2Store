package com.hcmute.g2store.repository;

import com.hcmute.g2store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("select s from Product s where s.subCategory.id = ?1")
    List<Product> findAllBySubCategory(Integer id);
    @Query("select s from Product s where s.provider.id = ?1")
    List<Product> findAllByProvider(Integer id);
}
