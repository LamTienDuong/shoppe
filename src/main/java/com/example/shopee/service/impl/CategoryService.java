package com.example.shopee.service.impl;

import com.example.shopee.entity.Category;
import com.example.shopee.repository.ICategoryRepository;
import com.example.shopee.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
