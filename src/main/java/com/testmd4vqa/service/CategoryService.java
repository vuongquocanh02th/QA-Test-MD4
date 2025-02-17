package com.testmd4vqa.service;

import com.testmd4vqa.model.Category;
import com.testmd4vqa.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    private final ICategoryRepository iCategoryRepository;
    public CategoryService(ICategoryRepository iCategoryRepository){
        this.iCategoryRepository = iCategoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return iCategoryRepository.findAll();
    }
}
