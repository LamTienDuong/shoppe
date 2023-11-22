package com.example.shopee.service;

import com.example.shopee.entity.OderDetail;

import java.util.List;

public interface IOderDetailService {
    List<OderDetail> findByOderId(int id);

    void create(OderDetail oderDetail);

    void delete(OderDetail oderDetail);

}
