package com.example.hibernate.service;


import com.example.hibernate.entities.Product;

import java.util.List;

public interface ProductService {

    void persistBatch();

    List<Product> getAll();
}
