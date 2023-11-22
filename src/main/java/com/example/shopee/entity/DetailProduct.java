package com.example.shopee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class DetailProduct {
    int id;
    int quantity;
}
