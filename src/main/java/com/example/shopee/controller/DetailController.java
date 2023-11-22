package com.example.shopee.controller;

import com.example.shopee.entity.CartProduct;
import com.example.shopee.entity.DetailProduct;
import com.example.shopee.entity.Product;
import com.example.shopee.service.ICartProductService;
import com.example.shopee.service.IProductService;
import com.example.shopee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.InvocableHandlerMethod;

import java.util.List;

@RestController
@RequestMapping("/detail")
@CrossOrigin("http://127.0.0.1:5500/")
public class DetailController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICartProductService cartProductService;
    @Autowired
    private IUserService userService;

    @PostMapping("{userId}")
    public ResponseEntity<?> insertCart(@PathVariable("userId") int id,@RequestBody DetailProduct detailProduct) {
        // Lấy ra danh sách trong sản phẩm của khách hàng
        List<CartProduct> cartProductList = cartProductService.findByUserId(id);

        if (!cartProductList.isEmpty()) {
            for (CartProduct cartProduct : cartProductList) {
                if (cartProduct.getProduct().getId() == detailProduct.getId()) {
                    // Sản phẩm đã có trong giỏ hàng thì tăng số lượng sản phẩm lên
                    int quantity = cartProduct.getQuantity();
                    cartProduct.setQuantity(quantity + detailProduct.getQuantity());

                    cartProductService.save(cartProduct);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        }

        CartProduct cartProduct1 = new CartProduct();
        //cartProduct1.setUser(userService.findById(id));

        cartProduct1.setUser(userService.findById(id));
        cartProduct1.setProduct(productService.findById(detailProduct.getId()));
        cartProduct1.setQuantity(detailProduct.getQuantity());

        // thêm sản phẩm vào giỏ hàng
        cartProductService.save(cartProduct1);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
