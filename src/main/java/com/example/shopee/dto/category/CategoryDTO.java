package com.example.shopee.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDTO {
    @NotNull(message = "Danh mục không được để trống.")
    private Integer id;
    private String name;
}
