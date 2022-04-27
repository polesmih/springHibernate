package com.example.hibernate.service;

import com.example.hibernate.dao.CustomerDao;


public interface CustomerService {

    void setCustomers(CustomerDao customerDao);
    void getPurchasesByCustomerId (int customerId);

}
