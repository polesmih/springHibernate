package com.example.hibernate.repository;

import com.example.hibernate.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Primary
@AllArgsConstructor
public class RepositoryProduct implements Repository<Product> {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public int create(Product product) {
        entityManager.persist(product);
        return entityManager.find(Product.class, product.getId()).getId();
    }

    @Override
    public Product selectById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> selectAll() {
        return entityManager
                .createQuery("Select a from Product a", Product.class)
                .getResultList();
    }

    @Override
    @Transactional
    public int update(Product product) {
        entityManager.merge(product);

        return 0;
    }

    @Override
    @Transactional
    public int delete(Product product) {
        entityManager.remove(product);

        return 0;
    }
}