package com.example.hibernate.repository;

import com.example.hibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Product, Long> {
}
