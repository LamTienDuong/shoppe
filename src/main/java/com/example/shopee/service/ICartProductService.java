package com.example.shopee.service;

import com.example.shopee.entity.CartProduct;

import java.util.List;

public interface ICartProductService {
    List<CartProduct> findByUserId(int id);

    CartProduct findById(int id);

    void delete(int idCartProduct);
}
