package com.example.hibernate.service.impl;

import com.example.hibernate.domain.Category;
import com.example.hibernate.domain.Product;
import com.example.hibernate.domain.dto.CategoryDTO;
import com.example.hibernate.domain.dto.ProductDTO;
import com.example.hibernate.exception.ShopException;
import com.example.hibernate.service.CategoryService;
import com.example.hibernate.service.ProductService;
import com.example.hibernate.service.ShopService;
import com.example.hibernate.util.FileUtils;
import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new ShopException("Категория не найдена"));

        product.setCategory(category);
        checkShopEntity(product);

        return new ProductDTO(productService.saveOrUpdate(product));
    }


    @Override
    public ProductDTO getProductByIdOrEmpty(int productId) {
        if (productId != 0) {
            return new ProductDTO(productService.findById(productId).orElse(new Product()));
        } else {
            return new ProductDTO(new Product());
        }
    }

    @Override
    public List<ProductDTO> getAllProducts(Map<String, String> filters) {
        if (isFiltersEmpty(filters)) {
            return productService.getAll().stream().map(ProductDTO::new).collect(Collectors.toList());
        } else {
            return getAllProductsFiltered(filters);
        }
    }

    private List<ProductDTO> getAllProductsFiltered(Map<String, String> filters) {
        String categoryTitle = filters.get(KEY_CATEGORY_NAME_FILTER);
        categoryService.findByTitle(categoryTitle).ifPresent(c -> filters.put(KEY_CATEGORY_ID, String.valueOf(c.getId())));

        return productService.getAllProductsFiltered(filters)
                .stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getAllProducts(Map<String, String> filters, Pageable pageable) {
        if (isFiltersEmpty(filters)) {
            Page<Product> productPage = productService.getAll(pageable);
            List<ProductDTO> productDTOS = productPage.stream().map(ProductDTO::new).collect(Collectors.toList());
            return new PageImpl<>(productDTOS, pageable, productPage.getTotalElements());
        } else {
            List<ProductDTO> productDTOS = getAllProductsFiltered(filters);
            return new PageImpl<>(productDTOS, pageable, productDTOS.size());
        }
    }

    private boolean isFiltersEmpty(Map<String, String> filters) {
        return filters.isEmpty() || (filters.keySet().size() == 1 && filters.get(KEY_PAGE_NUMBER) != null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            productDTO.setImageLink(pathImage.toString());
        }
        return saveOrUpdateProduct(productDTO);
    }

    @Override
    public void deleteProductByID(int productId) {
        log.info("product {}", productService.findById(productId).isPresent());
        productService.findById(productId).ifPresent(productService::deleteProduct);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException {
        Category category = new Category(categoryDTO);
        checkShopEntity(category);
        return categoryService.saveOrUpdate(new Category(categoryDTO));
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
