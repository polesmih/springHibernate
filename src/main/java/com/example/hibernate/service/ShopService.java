package com.example.hibernate.service;

import com.example.hibernate.domain.Category;
import com.example.hibernate.domain.dto.CategoryDTO;
import com.example.hibernate.domain.dto.ProductDTO;
import com.example.hibernate.exception.ShopException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface ShopService {

    ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException;
    ProductDTO getProductByIdOrEmpty(int productId);
    List<ProductDTO> getAllProducts(Map<String, String> filters);
    Page<ProductDTO> getAllProducts(Map<String, String> filters, Pageable pageable);
    ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image);

    void deleteProductByID(int productId);

    List<CategoryDTO> getAllCategories();
    Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException;
}
