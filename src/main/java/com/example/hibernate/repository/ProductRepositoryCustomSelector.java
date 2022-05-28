package com.example.hibernate.repository;

import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductRepositoryCustomSelector {


    Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable);
}

