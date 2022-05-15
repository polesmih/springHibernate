package com.example.hibernate.repository;

import com.example.hibernate.domain.Category;
import com.example.hibernate.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustomSelector{

    List<Product> findAllByCategory(Category category);
}
