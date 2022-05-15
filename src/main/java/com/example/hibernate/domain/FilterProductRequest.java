package com.example.hibernate.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterProductRequest {
    private int currPage = 0;
    private int pageSize = 5;

    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortField = "id";

    private BigDecimal minCost;
    private BigDecimal maxCost;
    private String categoryTitle;
    private Long categoryId;
    private String title;
}
