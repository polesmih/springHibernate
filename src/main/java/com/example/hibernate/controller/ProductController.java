package com.example.hibernate.controller;

import com.example.hibernate.entities.Product;
import com.example.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("products/delete/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }

    @PostMapping("/products/save_or_update")
    public void saveOrUpdateProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/products/coast_between")
    public List<Product> findAllByCoastBetween(@RequestParam int min, @RequestParam int max) {
        return productService.findAllByCoastBetween(min, max);
    }
}
