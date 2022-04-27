package com.example.hibernate.dao.impl;

import com.example.hibernate.dao.ProductDao;
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
public class ProductDaoImpl implements ProductDao<Product> {

    private final EntityManager entityManager;


    @Override
    @Transactional
    public void create(Product product) {
        entityManager.persist(product);
    }

    @Override
    public List<Product> findAll() {
        return entityManager
                .createQuery("Select a from products a", Product.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);

    }

    @Override
    @Transactional
    public Product saveOrUpdate(Product product) {
        return entityManager.merge(product);
    }

    @Override
    @Transactional
    public void getCustomersByProducts(int productId) {
        entityManager.
                createQuery("select a.customer.name from purchases a where a.product.id = :id_product")
                .setParameter("id_product", productId)
                .getResultList();

    }

}
