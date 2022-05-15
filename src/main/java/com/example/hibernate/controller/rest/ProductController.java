package com.example.hibernate.controller.rest;

import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.ShopErrorRestMessage;
import com.example.hibernate.domain.dto.ProductRestDTO;
import com.example.hibernate.exception.ShopEntityNotFoundException;
import com.example.hibernate.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.hibernate.util.ShopConstants.PRODUCT_URL;
import static com.example.hibernate.util.ShopConstants.REST_URL_V1;

@RestController("restProductController")
@RequestMapping(REST_URL_V1)
@RequiredArgsConstructor
public class ProductController {

    private final ShopService shopService;

    @GetMapping(PRODUCT_URL + "/{productId}")
    public ProductRestDTO getProduct(@PathVariable(name = "productId") Long productId) {
        return shopService.getProductByIdForRest(productId);
    }

    @PostMapping(PRODUCT_URL + "/list")
    public Page<ProductRestDTO> getProducts(@RequestBody FilterProductRequest filterProductRequest) {
        return shopService.getAllProductsPageable(filterProductRequest);
    }

    @PostMapping(PRODUCT_URL)
    public ResponseEntity<ProductRestDTO> addProduct(@RequestBody ProductRestDTO productRestDTO) {
        return ResponseEntity.ok(shopService.saveProduct(productRestDTO));

    }

    @PutMapping(PRODUCT_URL)
    public ProductRestDTO updateProduct(@RequestBody ProductRestDTO productRestDTO) {
        return shopService.updateProduct(productRestDTO);
    }

    @DeleteMapping(PRODUCT_URL + "/{productId}")
    public void deleteProduct(@PathVariable(name = "productId") Long productId) {
        shopService.deleteProductByID(productId);
    }

    @PostMapping(PRODUCT_URL + "/{productId}/image")
    public ResponseEntity<Void> uploadProductImage(@PathVariable(name = "productId") Long productId,
                                                   @RequestParam(value = "image", required = false) MultipartFile image) {
        shopService.addImageToProduct(productId, image);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ShopEntityNotFoundException.class)
    public ResponseEntity<ShopErrorRestMessage> handleException(ShopEntityNotFoundException e) {
        ShopErrorRestMessage error = new ShopErrorRestMessage(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
