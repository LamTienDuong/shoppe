package com.example.shopee.controller;


import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Category;
import com.example.shopee.entity.Product;
import com.example.shopee.service.ICategoryService;
import com.example.shopee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorys")
@CrossOrigin("http://127.0.0.1:5500/")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK) ;
    }

}
