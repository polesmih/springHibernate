package com.example.hibernate.service;

import com.example.hibernate.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
}
