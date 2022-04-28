package com.example.hibernate.repository;


import com.example.hibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(int id);

    List<Product> findAll();

    void deleteById(int id);

    Product save(Product product);

    @Query("select p from products p where p.price < :max AND p.price> :min")
    List<Product> findAllByPriceBetween(int min, int max);

    @Query("select p from products p where p.price < :max")
    List<Product> findAllLowCost(int max);

    @Query("select p from products p where p.price > :min")
    List<Product> findAllHighCost(int min);


}


