package com.testmd4vqa.service;

import com.testmd4vqa.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    void deleteProduct(Long id);
}
