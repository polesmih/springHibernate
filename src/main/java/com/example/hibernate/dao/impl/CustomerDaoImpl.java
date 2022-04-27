package com.example.hibernate.dao.impl;

import com.example.hibernate.dao.CustomerDao;
import com.example.hibernate.entities.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.List;

@Component
//@Primary
@AllArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private final EntityManager entityManager;

    @Override
    public List<Purchase> getPurchasesByCustomerId(int customerId) {
        return entityManager.createQuery("select o from purchases o where o.customer.id = :customerId", Purchase.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
