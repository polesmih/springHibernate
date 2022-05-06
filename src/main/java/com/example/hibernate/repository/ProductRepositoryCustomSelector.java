package com.example.hibernate.repository;

import com.example.hibernate.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductRepositoryCustomSelector {

    List<Product> getAllProductsFiltered(Map<String, String> filters);
    List<Product> getAllProductsFiltered(Map<String, String> filters, Pageable pageable);
}
