package com.example.hibernate.domain.dto;

import com.example.hibernate.domain.Product;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal cost;
    private Long categoryId;
    private String categoryName;
    private String imageLink;

    public ProductDTO(Product that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
        imageLink = that.getImageLink();
        if (that.getCategory() != null) {
            categoryId = that.getCategory().getId();
            categoryName = that.getCategory().getTitle();
        }
    }

    public ProductDTO(ProductDTO that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
        imageLink = that.getImageLink();
        categoryId = that.getCategoryId();
        categoryName = that.getCategoryName();
    }
}