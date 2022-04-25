package com.example.hibernate.service;

import com.example.hibernate.entities.Product;
import com.example.hibernate.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    private final Repository<Product> repository;
    private final Repository<Product> repositorySelectProduct;

    @Autowired
    public ProductServiceImpl(Repository<Product> repository,
                              @Qualifier("RepositorySelectProduct") Repository<Product> repositorySelectProduct) {
        this.repository = repository;
        this.repositorySelectProduct = repositorySelectProduct;
    }

    @Override
    public void persistBatch() {
        List<Integer> idList = createProduct();
        updateProduct(idList);
        //deleteProduct(idList);
    }

    @Override
    public List<Product> getAll() {
        //return repository.selectAll();
        return repositorySelectProduct.selectAll();
    }

    private List<Integer> createProduct() {
        int id1 = repository.create(new Product().setName("First one").setType("Type first"));
        int id2 = repository.create(new Product().setName("Second one").setType("Type second"));
        int id3 = repository.create(new Product().setName("Third one").setType("Type third"));
        int id4 = repository.create(new Product().setName("Fourth one").setType("Type Fourth"));

        return Arrays.asList(id1, id2, id3, id4);
    }

    private void updateProduct(List<Integer> productList) {
        productList.forEach(e -> {
            Product product = repository.selectById(e);
            product.setType("Common type");
            repository.update(product);
        });
    }

    private void deleteProduct(List<Integer> productList) {
        productList.forEach(e -> {
            Product product = repository.selectById(e);
            repository.delete(product);
        });
    }
}
