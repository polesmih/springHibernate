package com.example.hibernate.service;


import com.example.hibernate.dao.ProductDao;
import com.example.hibernate.entities.Product;

import java.util.List;

public interface ProductService {

    void setProductDao(ProductDao<Product> productDao);
    Product getProductById(int id);
    List<Product> getListProduct();
    void deleteById(int id);
    void create(Product product);
    void getCustomersByProducts(int productId);

}
