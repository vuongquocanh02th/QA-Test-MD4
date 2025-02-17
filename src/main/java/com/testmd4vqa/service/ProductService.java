package com.testmd4vqa.service;

import com.testmd4vqa.model.Product;
import com.testmd4vqa.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private final IProductRepository iProductRepository;
    public ProductService(IProductRepository iProductRepository){
        this.iProductRepository = iProductRepository;
    }


    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRepository.deleteById(id);
    }
}
