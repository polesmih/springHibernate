package com.example.hibernate.domain.dto;

import com.example.hibernate.domain.Category;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private int id;
    private String title;

    public CategoryDTO(Category that) {
        id = that.getId();
        title = that.getTitle();
    }
}
