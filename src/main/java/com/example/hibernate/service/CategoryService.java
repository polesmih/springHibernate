package com.example.hibernate.service;

import com.example.hibernate.repository.CategoryRepository;
import com.example.hibernate.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
