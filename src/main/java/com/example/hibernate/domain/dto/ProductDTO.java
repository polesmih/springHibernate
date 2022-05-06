package com.example.hibernate.domain.dto;

import com.example.hibernate.domain.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private int id;
    private String title;
    private int cost;
    private int categoryId;
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
}
