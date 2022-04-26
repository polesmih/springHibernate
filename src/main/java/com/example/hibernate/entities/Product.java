package com.example.hibernate.entities;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "price")
    private int price;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}
