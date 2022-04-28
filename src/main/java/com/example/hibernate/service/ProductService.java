package com.example.hibernate.service;


import com.example.hibernate.entities.Product;
import com.example.hibernate.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }


    public List<Product> findAllByCoastBetween(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max);
    }
}