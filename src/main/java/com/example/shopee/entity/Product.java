package com.example.shopee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image", columnDefinition="longtext")
    private String image;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
