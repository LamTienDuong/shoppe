package com.example.shopee.controller;

import com.example.shopee.entity.CartProduct;
import com.example.shopee.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://127.0.0.1:5500/")
public class CartController {
    @Autowired
    private ICartProductService cartProductService;

    @GetMapping("{user_id}")
    public ResponseEntity<List<CartProduct>> getCartProducts(@PathVariable("user_id") int id) {
        return new ResponseEntity<>(cartProductService.findByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("{idCartProduct}")
    public ResponseEntity<CartProduct> delete(@PathVariable("idCartProduct") int idCartProduct) {
        CartProduct cartProduct = cartProductService.findById(idCartProduct);
        if (cartProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartProductService.delete(idCartProduct);
        return new ResponseEntity<>(cartProduct, HttpStatus.OK);
    }
}
