package com.example.hibernate.service.impl;

import com.example.hibernate.domain.Category;
import com.example.hibernate.repository.CategoryRepository;
import com.example.hibernate.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findCategoryByTitleIgnoreCase(title).stream().findFirst();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.saveAndFlush(category);
    }
}
