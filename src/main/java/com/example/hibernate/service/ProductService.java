package com.example.hibernate.service;

import com.example.hibernate.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Page<Product> getAll(Pageable pageable);
    Optional<Product> findById(int id);
    Product saveOrUpdate(Product product);
    List<Product> getAllProductsFiltered(Map<String, String> filters);
    List<Product> getAllProductsFiltered(Map<String, String> filters, Pageable pageable);
    void deleteProduct(Product product);
}
