package online_store.controller.rest;

import lombok.RequiredArgsConstructor;
import online_store.domain.FilterProductRequest;
import online_store.domain.ShopErrorRestMessage;
import online_store.domain.dto.ProductRestDTO;
import online_store.exception.ShopEntityNotFoundException;
import online_store.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static online_store.util.ShopConstants.PRODUCT_URL;
import static online_store.util.ShopConstants.REST_URL_V1;

@RestController("restProductController")
@RequestMapping(REST_URL_V1 + PRODUCT_URL)
@RequiredArgsConstructor
public class ProductController {

    private final ShopService shopService;

    @GetMapping("/{productId}")
    public ProductRestDTO getProduct(@PathVariable(name = "productId") Long productId) {
        return shopService.getProductByIdForRest(productId);
    }

    @PostMapping("/list")
    public Page<ProductRestDTO> getProducts(@RequestBody FilterProductRequest filterProductRequest) {
        return shopService.getAllProductsPageable(filterProductRequest);
    }

    @PostMapping()
    public ResponseEntity<ProductRestDTO> addProduct(@RequestBody ProductRestDTO productRestDTO) {
        return ResponseEntity.ok(shopService.saveProduct(productRestDTO));

    }

    @PutMapping()
    public ProductRestDTO updateProduct(@RequestBody ProductRestDTO productRestDTO) {
        return shopService.updateProduct(productRestDTO);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable(name = "productId") Long productId) {
        shopService.deleteProductByID(productId);
    }

    @PostMapping("/{productId}/image")
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
