package com.example.hibernate.dao;

import com.example.hibernate.entities.Purchase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerDao {
    List<Purchase> getPurchasesByCustomerId (int customerId);
}
