package com.example.hibernate.service;


import com.example.hibernate.entities.Product;
import com.example.hibernate.dao.ProductDao;

import java.util.List;

public interface ProductService {

    void setProductDao(ProductDao<Product> productDao);
    Product getProductById(Long id);
    List<Product> getListProduct();
    void deleteById(Long id);
    void create(Product product);

}
