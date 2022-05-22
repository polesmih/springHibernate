package com.example.hibernate.repository;

import com.example.hibernate.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByTitleIgnoreCase(String title);
    List<Category> findCategoriesByParentCategoryIsNull();
}
