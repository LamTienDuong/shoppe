package com.example.shopee.controller;

import com.example.shopee.entity.*;
import com.example.shopee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://127.0.0.1:5500/")
public class CartController {
    @Autowired
    private ICartProductService cartProductService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOderService oderService;
    @Autowired
    private IOderDetailService oderDetailService;

    @GetMapping("{user_id}")
    public ResponseEntity<List<CartProduct>> getCartProducts(@PathVariable("user_id") int id) {
        return new ResponseEntity<>(cartProductService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("{user_id}")
    public ResponseEntity<?> buyProduct(@PathVariable("user_id") int id, @RequestBody ArrayProduct products) {
        LocalDate date = LocalDate.now();
        Oder oder = new Oder();
        oder.setOrderDate(date);
        oder.setUser(userService.findById(id));

        // Tạo mới hóa đơn lưu vào database
        oderService.create(oder);

        // Tạo ra các oderDetail thêm vào data
        OderDetail oderDetail;
        for (String e : products.getArrayProduct()) {
            oderDetail = new OderDetail();
            // Tìm giỏ hàng trong database
            CartProduct cartProduct = cartProductService.findById(Integer.parseInt(e));
            oderDetail.setPrice(cartProduct.getProduct().getPrice());
            oderDetail.setQuantity(cartProduct.getQuantity());

            // Tìm sản phẩm trong database
            Product product = productService.findById(cartProduct.getProduct().getId());
            oderDetail.setProduct(product);

            // Them hóa đơn vừa tạo vào chi tiết hóa đơn
            oderDetail.setOder(oder);

            oderDetailService.create(oderDetail);

            // Xóa cartProduct
            cartProductService.delete(cartProduct.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
