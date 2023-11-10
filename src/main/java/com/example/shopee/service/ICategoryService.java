package com.example.shopee.service;

import com.example.shopee.dto.category.CategoryDTO;
import com.example.shopee.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
}
