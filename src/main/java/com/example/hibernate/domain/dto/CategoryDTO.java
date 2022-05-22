package com.example.hibernate.domain.dto;

import com.example.hibernate.domain.Category;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private Long id;
    private String title;
    private Long parentCategoryId;

    public CategoryDTO(Category that) {
        id = that.getId();
        title = that.getTitle();
        if (that.getParentCategory() != null) {
            parentCategoryId = that.getParentCategory().getId();
        }
    }
}
