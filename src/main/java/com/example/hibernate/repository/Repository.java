package com.example.hibernate.repository;

import com.example.hibernate.entities.Product;

import java.util.List;

public interface Repository<T> {

    int create(T obj);

    T selectById(int id);

    List<T> selectAll();

    int update(T obj);

    int delete(Product product);
}
