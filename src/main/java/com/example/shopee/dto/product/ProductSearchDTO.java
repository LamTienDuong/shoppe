package com.example.shopee.dto.product;

import com.example.shopee.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDTO {
    private String title = "";
    private String categoryId = "";
}
