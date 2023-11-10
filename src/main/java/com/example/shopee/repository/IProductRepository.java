package com.example.shopee.repository;

import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "FROM Product p where p.title like concat('%', :ten, '%')")
    Page<Product> search(@Param("ten") String ten, Pageable pageable);
}
