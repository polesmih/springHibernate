package online_store.domain;

import lombok.*;
import online_store.domain.dto.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


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
        private Long id;

        @NotBlank(message = "Имя продукта обязательно")
        @Column(name = "title")
        private String title;

        @NotNull(message = "Цена продукта обязательна")
        @Column(name = "cost")
        private BigDecimal cost;

        @Column(name = "image_link")
        private String imageLink;

        @ManyToOne
        @JoinColumn(name = "category_id")
        @NotNull(message = "Категория обязательна")
        private Category category;

        public Product(ProductDTO that) {
                id = that.getId()== null ? -1 : that.getId();
                title = that.getTitle();
                cost = that.getCost();
                imageLink = that.getImageLink();
        }
}
