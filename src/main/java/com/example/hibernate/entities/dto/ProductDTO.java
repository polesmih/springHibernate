package com.example.hibernate.entities.dto;

import com.example.hibernate.entities.Product;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private long id;
    private String title;
    private int price;

    public ProductDTO(Product that) {
        id = that.getId();
        title = that.getTitle();
        price = that.getPrice();
    }
}