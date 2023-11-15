package com.example.shopee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "card_product")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Column(name = "product_id")
    @ManyToOne
    private Product product;

//    @Column(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "quantity")
    private int quantity;
}
