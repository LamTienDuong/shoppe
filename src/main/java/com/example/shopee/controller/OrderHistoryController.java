package com.example.shopee.controller;

import com.example.shopee.entity.Oder;
import com.example.shopee.entity.OderDetail;
import com.example.shopee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin("http://127.0.0.1:5500/")
public class OrderHistoryController {
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

    @GetMapping("{userId}")
    public ResponseEntity<List<Oder>> getOderDetails(@PathVariable("userId") int id) {
        return new ResponseEntity<>(oderService.findByUserId(id),HttpStatus.OK);
    }

}
