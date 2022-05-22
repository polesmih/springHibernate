package com.example.hibernate.service;

import com.example.hibernate.domain.Category;
import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.dto.CategoryDTO;
import com.example.hibernate.domain.dto.CategoryWithChildsDTO;
import com.example.hibernate.domain.dto.ProductDTO;
import com.example.hibernate.domain.dto.ProductRestDTO;
import com.example.hibernate.exception.ShopEntityNotFoundException;
import com.example.hibernate.exception.ShopException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;


public interface ShopService {

    ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException;
    ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image);
    void addImageToProduct(Long productId, MultipartFile image) throws ShopEntityNotFoundException;
    ProductRestDTO saveProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException;
    ProductRestDTO updateProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException;
    ProductDTO getProductByIdOrEmpty(Long productId);
    ProductRestDTO getProductByIdForRest(Long productId) throws ShopEntityNotFoundException;
    Page<ProductRestDTO> getAllProductsPageable(FilterProductRequest filterProductRequest);

    void deleteProductByID(Long productId);

    List<CategoryDTO> getAllCategories();
    Category saveOrUpdateCategoryWithoutParent(CategoryDTO categoryDTO) throws ShopException;
    CategoryDTO addCategory(CategoryDTO categoryDTO) throws ShopEntityNotFoundException;
    CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ShopEntityNotFoundException;
    void deleteCategory(Long categoryId) throws ShopEntityNotFoundException;
    CategoryDTO getCategoryDTOById(Long categoryId) throws ShopEntityNotFoundException;
    List<CategoryWithChildsDTO> getAllCategoryTrees();
}