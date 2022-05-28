package com.example.hibernate.controller.mvc;

import com.example.hibernate.domain.FilterProductRequest;
import com.example.hibernate.domain.dto.ProductDTO;
import com.example.hibernate.domain.dto.ProductRestDTO;
import com.example.hibernate.exception.ShopException;
import com.example.hibernate.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {


    private final ShopService shopService;

    @GetMapping
    public String getProducts(Model model,
                              FilterProductRequest filterProductRequest) {
        log.info("request params {}", filterProductRequest);

        Page<ProductRestDTO> page = shopService.getAllProductsPageable(filterProductRequest);
        model.addAttribute("page", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        model.addAttribute("filter", filterProductRequest);
        model.addAttribute("categories", shopService.getAllCategories());
        return "products";
    }


    @GetMapping(value = "/form")
    public String getProduct(@RequestParam(name = "id", required = false) Long id, Model model) {
        prepareModelForForm(model, shopService.getProductByIdOrEmpty(id), "");
        return "one_product";
    }

    @PostMapping
    public String saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO,
                                      @RequestParam(value = "image", required = false) MultipartFile image,
                                      Model model) {
        try {
            shopService.saveWithImage(productDTO, image);
        } catch (ShopException e) {
            prepareModelForForm(model, productDTO, e.getMessage());
            return "one_product";
        }
        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name = "id") Long productId) {
        shopService.deleteProductByID(productId);
        return "redirect:/product";
    }

    private Model prepareModelForForm(Model model, ProductDTO productDTO, String errors) {
        model.addAttribute("product", productDTO);
        model.addAttribute("errors", errors);
        model.addAttribute("categories", shopService.getAllCategories());
        return model;
    }

}