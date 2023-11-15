package com.example.shopee.repository;

import com.example.shopee.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICartProductRepository extends JpaRepository<CartProduct, Integer> {
    @Query(value = "from CartProduct cp where cp.user.id = :userId")
    List<CartProduct> findByUserId(int userId);

}
