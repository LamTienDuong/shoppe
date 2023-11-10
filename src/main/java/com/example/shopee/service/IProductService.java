package com.example.shopee.service;

import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Product;
import com.example.shopee.service.impl.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Page<Product> findAll(ProductSearchDTO productSearchDTO, Pageable pageable);

    Product findById(int id);

    void create(Product product);

    void update(Product product);

    void delete(int id);
}
