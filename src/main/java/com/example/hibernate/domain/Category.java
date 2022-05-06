package com.example.hibernate.domain;

import com.example.hibernate.domain.dto.CategoryDTO;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
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
    private int id;

    @Column(name = "title")
    @NotBlank(message = "Имя категории обязательно")
    private String title;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;

    @Column(name="parent_category_id")
    private Long parentId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_category_id")
    @ToString.Exclude
    private Set<Category> childCategories;

    public Category(CategoryDTO that) {
        id = that.getId();
        title = that.getTitle();
    }

}
