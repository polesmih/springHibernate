package com.example.hibernate.controller.rest;


import com.example.hibernate.domain.ShopErrorRestMessage;
import com.example.hibernate.domain.dto.CategoryDTO;
import com.example.hibernate.domain.dto.CategoryWithChildsDTO;
import com.example.hibernate.exception.ShopEntityNotFoundException;
import com.example.hibernate.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.hibernate.util.ShopConstants.CATEGORY_URL;
import static com.example.hibernate.util.ShopConstants.REST_URL_V1;

@RestController("restCategoryController")
@RequestMapping(REST_URL_V1 + CATEGORY_URL)
@RequiredArgsConstructor
public class CategoryController {

    private final ShopService shopService;

    @PostMapping()
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return shopService.addCategory(categoryDTO);
    }

    @PutMapping()
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return shopService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        shopService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable Long categoryId){
        return shopService.getCategoryDTOById(categoryId);
    }

    @GetMapping()
    public List<CategoryWithChildsDTO> getAllCategoryTree(){
        return shopService.getAllCategoryTrees();
    }

    @ExceptionHandler(ShopEntityNotFoundException.class)
    public ResponseEntity<ShopErrorRestMessage> handleException(ShopEntityNotFoundException e) {
        ShopErrorRestMessage error = new ShopErrorRestMessage(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
