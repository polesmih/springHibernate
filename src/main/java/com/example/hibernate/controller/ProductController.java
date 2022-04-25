package com.example.hibernate.controller;

import com.example.hibernate.entities.Product;
import com.example.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAll() {
       return productService.getAll();
    }

    @PostMapping("/persistBatch")
    public void persistBatch() {
        productService.persistBatch();
    }

}
