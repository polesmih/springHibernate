package com.example.hibernate.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryWithChildsDTO {

    private Long id;
    private String title;
    private List<CategoryWithChildsDTO> childs = new LinkedList<>();

    public CategoryWithChildsDTO(Long id, String title){
        this.id = id;
        this.title = title;
    }

}
