package com.example.shopee.service;

import com.example.shopee.entity.Oder;

import java.util.List;

public interface IOderService {
    Integer getMonthlySalesCount(int year, int month);

    List<Oder> findByUserId(int id);

    void create(Oder oder);
}
