package com.example.shopee.service.impl;

import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Product;
import com.example.shopee.repository.IProductRepository;
import com.example.shopee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public Page<Product> findAll(ProductSearchDTO productSearchDTO, Pageable pageable) {
        if (productSearchDTO.getTitle() == null) {
            productSearchDTO.setTitle("");
        }
        return productRepository.search(productSearchDTO.getTitle(), pageable);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(findById(id));
    }
}
