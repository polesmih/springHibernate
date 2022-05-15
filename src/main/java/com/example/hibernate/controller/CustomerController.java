package com.example.hibernate.controller;

import com.example.hibernate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customerPurchases/{id}")
    public void getOrdersByIdCustomer(@PathVariable int id) {
        customerService.getPurchasesByCustomerId(id);
    }
}
