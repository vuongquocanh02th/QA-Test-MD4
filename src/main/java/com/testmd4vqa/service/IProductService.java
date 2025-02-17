package com.testmd4vqa.service;

import com.testmd4vqa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    Page<Product> searchProducts(String name, Long category, String status, Pageable pageable);
}
