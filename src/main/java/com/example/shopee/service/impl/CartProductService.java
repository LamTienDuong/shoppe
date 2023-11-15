package com.example.shopee.service.impl;

import com.example.shopee.entity.CartProduct;
import com.example.shopee.repository.ICartProductRepository;
import com.example.shopee.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService implements ICartProductService {
    @Autowired
    private ICartProductRepository cartProductRepository;

    @Override
    public List<CartProduct> findByUserId(int id) {
        return cartProductRepository.findByUserId(id);
    }

    public CartProduct findById(int id) {
        return cartProductRepository.findById(id).orElse(null);
    }

    public void delete(int idCartProduct) {
        cartProductRepository.delete(findById(idCartProduct));
    }
}
