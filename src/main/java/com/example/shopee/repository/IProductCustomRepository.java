package com.example.shopee.repository;

import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductCustomRepository {
    Page<Product> search(ProductSearchDTO productSearchDTO, Pageable pageable);
}
