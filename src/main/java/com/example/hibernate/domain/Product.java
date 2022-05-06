package com.example.hibernate.domain;

import com.example.hibernate.domain.dto.ProductDTO;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Имя продукта обязательно")
    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductDTO that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
        imageLink = that.getImageLink();
    }
}
