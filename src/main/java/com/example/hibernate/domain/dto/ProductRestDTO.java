package com.example.hibernate.domain.dto;


import com.example.hibernate.domain.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductRestDTO extends ProductDTO {

    private String saveImageLink = null;

    public ProductRestDTO(Product product) {
        super(product);
    }

    public ProductRestDTO(ProductDTO productDTO) {
        super(productDTO);
    }


}