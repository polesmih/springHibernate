package com.example.hibernate.service.impl;

import com.example.hibernate.domain.Category;
import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.Product;
import com.example.hibernate.domain.dto.CategoryDTO;
import com.example.hibernate.domain.dto.CategoryWithChildsDTO;
import com.example.hibernate.domain.dto.ProductDTO;
import com.example.hibernate.domain.dto.ProductRestDTO;
import com.example.hibernate.exception.ShopEntityNotFoundException;
import com.example.hibernate.exception.ShopException;
import com.example.hibernate.service.CategoryService;
import com.example.hibernate.service.ProductService;
import com.example.hibernate.service.ShopService;
import com.example.hibernate.util.FileUtils;
import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.hibernate.util.ShopConstants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Validator validator;

    @Override
    public ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException {
        Product product = new Product(productDTO);
        Category category = categoryService.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopEntityNotFoundException("Категория не найдена"));

        product.setCategory(category);
        checkShopEntity(product);

        return new ProductDTO(productService.saveOrUpdate(product));
    }


    @Override
    public ProductDTO getProductByIdOrEmpty(Long productId) {
        if (productId != null) {
            return new ProductDTO(productService.findById(productId).orElse(new Product()));
        } else {
            return new ProductDTO(new Product());
        }
    }

    @Override
    public ProductRestDTO getProductByIdForRest(Long productId) throws ShopEntityNotFoundException {
        ProductRestDTO productRestDTO = new ProductRestDTO(productService.findById(productId)
                .orElseThrow(() -> new ShopEntityNotFoundException("Продукт не найден")));
        return updateUploadImageLink(productRestDTO);
    }

    @Override
    public Page<ProductRestDTO> getAllProductsPageable(FilterProductRequest filterProductRequest) {

        if (filterProductRequest.getCategoryTitle() != null && !filterProductRequest.getCategoryTitle().isBlank()) {
            categoryService.findByTitle(filterProductRequest.getCategoryTitle())
                    .ifPresent(c -> filterProductRequest.setCategoryId(c.getId()));
        }

        Pageable pageable = generatePageable(filterProductRequest);

        Page<Product> products = productService.getAllProductsFiltered(filterProductRequest, pageable);
        return new PageImpl<>(products.stream().map(ProductRestDTO::new).collect(Collectors.toList()), pageable, products.getTotalElements());
    }

    private Pageable generatePageable(FilterProductRequest filterProductRequest) {
        return PageRequest.of(filterProductRequest.getCurrPage(), filterProductRequest.getPageSize()
                , Sort.by(filterProductRequest.getSortDirection(), filterProductRequest.getSortField()));
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            productDTO.setImageLink(pathImage.toString());
        }
        return saveOrUpdateProduct(productDTO);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void addImageToProduct(Long productId, MultipartFile image) {
        saveWithImage(getProductByIdForRest(productId), image);
    }

    @Override
    public ProductRestDTO saveProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException {
        productRestDTO.setSaveImageLink(null);
        productRestDTO.setId(null);
        return updateProduct(productRestDTO);

    }

    @Override
    public ProductRestDTO updateProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException {
        getProductByIdForRest(productRestDTO.getId());
        productRestDTO = new ProductRestDTO(saveOrUpdateProduct(productRestDTO));
        return updateUploadImageLink(productRestDTO);
    }

    @Override
    public void deleteProductByID(Long productId) {
        log.info("product {}", productService.findById(productId).isPresent());
        productService.findById(productId).ifPresent(productService::deleteProduct);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public Category saveOrUpdateCategoryWithoutParent(CategoryDTO categoryDTO) throws ShopException {
        Category category = new Category(categoryDTO);
        checkShopEntity(category);
        return categoryService.saveOrUpdate(new Category(categoryDTO));
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) throws ShopException {
        categoryDTO.setId(null);
        return new CategoryDTO(saveOrUpdateCategory(categoryDTO));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ShopException {
        if (categoryDTO.getParentCategoryId().equals(categoryDTO.getId())) {
            categoryDTO.setParentCategoryId(null);
        }
        categoryDTO.setId(getCategoryById(categoryDTO.getId()).getId());
        return new CategoryDTO(saveOrUpdateCategory(categoryDTO));
    }

    private Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException {
        Category parentCategory = null;
        if (categoryDTO.getParentCategoryId() != null) {
            parentCategory = categoryService.findById(categoryDTO.getParentCategoryId())
                    .orElseThrow(() -> new ShopEntityNotFoundException("Не найдена родительская категория с таким ID"));
        }
        Category category = new Category(categoryDTO);
        category.setParentCategory(parentCategory);
        checkShopEntity(category);
        return categoryService.saveOrUpdate(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws ShopEntityNotFoundException {
        Category category = getCategoryById(categoryId);
        categoryService.delete(category);
    }

    @Override
    public CategoryDTO getCategoryDTOById(Long categoryId) throws ShopEntityNotFoundException {
        return new CategoryDTO(getCategoryById(categoryId));
    }

    @Override
    public List<CategoryWithChildsDTO> getAllCategoryTrees() {
        return categoryService.getAllWithoutParents().stream().map(this::convertToCategoryWithChildNode).collect(Collectors.toList());
    }

    public CategoryWithChildsDTO convertToCategoryWithChildNode(Category category) {
        CategoryWithChildsDTO categoryDTO = new CategoryWithChildsDTO(category.getId(), category.getTitle());
        if (!category.getSubCategories().isEmpty()) {
            category.getSubCategories().forEach(
                    c -> categoryDTO.getChilds().add(convertToCategoryWithChildNode(c))
            );
        }
        return categoryDTO;
    }

    private Category getCategoryById(Long categoryId) throws ShopEntityNotFoundException {
        return categoryService.findById(categoryId).orElseThrow(() -> new ShopEntityNotFoundException("Не найдена категория с таким ID"));
    }

    private ProductRestDTO updateUploadImageLink(ProductRestDTO productRestDTO) {
        if (productRestDTO.getId() != null) {
            productRestDTO.setSaveImageLink(String.format(IMAGE_UPLOAD_LINK_PATTERN_V1, productRestDTO.getId()));
        }
        return productRestDTO;
    }

    private <T> void checkShopEntity(T t) throws ShopException {
        String errorsString = validator.validate(t).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
        if (!errorsString.isEmpty()) {
            throw new ShopException(errorsString);
        }
    }

}