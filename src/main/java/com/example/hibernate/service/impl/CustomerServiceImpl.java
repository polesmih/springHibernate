package com.example.hibernate.service.impl;

import com.example.hibernate.dao.CustomerDao;
import com.example.hibernate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public void setCustomers(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public void getPurchasesByCustomerId (int customerId) {
        customerDao.getPurchasesByCustomerId(customerId);
    }

}
