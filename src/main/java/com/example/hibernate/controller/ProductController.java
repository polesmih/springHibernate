package com.example.hibernate.controller;

import com.example.hibernate.entities.Product;
import com.example.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> showAllProducts() {
        return productService.getListProduct();

    }

    @GetMapping("product/delete/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }


    @PostMapping("/product/addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.create(product);
    }

    @GetMapping("/products/productCustomers/{id}")
    public void getCustomers(@PathVariable int id) {
        productService.getCustomersByProducts(id);
    }
}
