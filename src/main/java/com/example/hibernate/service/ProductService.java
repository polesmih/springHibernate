package com.example.hibernate.service;

import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Page<Product> getAll(Pageable pageable);
    Optional<Product> findById(Long id);
    Product saveOrUpdate(Product product);
    Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable);
    void deleteProduct(Product product);
}
