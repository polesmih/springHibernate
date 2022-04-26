package com.example.hibernate.service.impl;

import com.example.hibernate.entities.Product;
import com.example.hibernate.dao.ProductDao;
import com.example.hibernate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    private  ProductDao<Product> productDao;

    @Autowired

    @Override
    public void setProductDao(ProductDao<Product> productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> getListProduct() {
        return productDao.findAll();
    }


    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }


    @Override
    public void create(Product product) {
        productDao.create(product);
    }

}
