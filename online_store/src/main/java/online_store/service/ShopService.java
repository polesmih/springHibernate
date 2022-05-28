package online_store.service;

import online_store.domain.Category;
import online_store.domain.FilterProductRequest;
import online_store.domain.dto.CategoryDTO;
import online_store.domain.dto.CategoryWithChildsDTO;
import online_store.domain.dto.ProductDTO;
import online_store.domain.dto.ProductRestDTO;
import online_store.exception.ShopEntityNotFoundException;
import online_store.exception.ShopException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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