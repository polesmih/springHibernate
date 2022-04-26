package com.example.hibernate.dao;

import com.example.hibernate.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao <T>{

        void create(T obj);
        List<T> findAll();
        T findById(Long id);
        void deleteById(Long id);
        Product saveOrUpdate(T obj);

}
