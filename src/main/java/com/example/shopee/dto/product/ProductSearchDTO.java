package com.example.shopee.dto.product;

import com.example.shopee.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@Setter
public class ProductSearchDTO {
    private String title = "";
    private String categoryId = "";
    private List<Integer> categoryIds;
    private String discount;
}
