package com.example.hibernate.service;

import com.example.hibernate.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();
    List<Category> getAllWithoutParents();
    Optional<Category> findById(Long id);
    Optional<Category> findByTitle(String title);
    Category saveOrUpdate(Category category);
    void delete(Category category);
}
