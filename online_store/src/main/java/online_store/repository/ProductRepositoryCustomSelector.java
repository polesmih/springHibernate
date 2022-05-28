package online_store.repository;

import online_store.domain.FilterProductRequest;
import online_store.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustomSelector {


    Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable);
}

