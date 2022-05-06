package com.example.hibernate.service;

import com.example.hibernate.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();
    Optional<Category> findById(int id);
    Optional<Category> findByTitle(String title);
    Category saveOrUpdate(Category category);
}
