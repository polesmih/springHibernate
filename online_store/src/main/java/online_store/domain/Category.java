package online_store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import online_store.domain.dto.CategoryDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Имя категории обязательно")
    private String title;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "parentCategory")
    @ToString.Exclude
    private Set<Category> subCategories;

    public Category(CategoryDTO that) {
        id = that.getId();
        title = that.getTitle();
    }

}
