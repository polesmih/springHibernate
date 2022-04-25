package com.example.hibernate.repository;

import com.example.hibernate.entities.Product;
//import com.example.hibernate.entities.Product_;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component("RepositorySelectProduct")
@AllArgsConstructor
public class RepositorySelectProduct implements Repository<Product> {
    private final EntityManager entityManager;

    @Override
    public List<Product> selectAll() {
        return selectAllJPQL();
        //return selectAllHQL();
        //return selectAllCriteriaApi();
        //return selectAllCriteriaApiMetaModel();
//        return selectAllNative();
    }

    private List<Product> selectAllJPQL() {
        return entityManager
                .createQuery("Select a from Product a", Product.class)
                .getResultList();
    }

    private List<Product> selectAllHQL() {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Product where id = :id");
        query.setParameter("id", 2);
        List<Product> list = query.getResultList();

        return list;
    }

    private List<Product> selectAllCriteriaApi() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
        cr.select(root);

        Query query = session.createQuery(cr);
        List<Product> results = query.getResultList();

        return results;
    }

//    private List<Product> selectAllCriteriaApiMetaModel() {
//        Session session = entityManager.unwrap(Session.class);
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
//
//        Root<Product> root = criteriaQuery.from(Product.class);
//        criteriaQuery.select(root).where(cb.equal(root.get(Product_.NAME), "Third one"));
//
//        Query query = session.createQuery(criteriaQuery);
//        List<Product> results = query.getResultList();
//
//        return results;
//    }

    private List<Product> selectAllNative() {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery<Product> query = session.createNativeQuery("select * from product");
        List<Product> rows = query.list();

        return new ArrayList<>(rows);
    }

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