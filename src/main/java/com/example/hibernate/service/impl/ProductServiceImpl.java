package com.example.hibernate.service.impl;

import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.Product;
import com.example.hibernate.repository.ProductRepository;
import com.example.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable) {
        return productRepository.getAllProductsFiltered(filterProductRequest, pageable);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}