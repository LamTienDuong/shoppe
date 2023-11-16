package com.example.shopee.service;

import com.example.shopee.dto.category.CategoryDTO;
import com.example.shopee.entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
    List<Object[]> getQuantityByCategory();
}
