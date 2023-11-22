package com.example.shopee.service.impl;

import com.example.shopee.entity.OderDetail;
import com.example.shopee.repository.IOderDetailRepository;
import com.example.shopee.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderDetailService implements IOderDetailService {
    @Autowired
    private IOderDetailRepository oderDetailRepository;

    @Override
    public List<OderDetail> findByOderId(int id) {
        return oderDetailRepository.findByOderId(id);
    }

    @Override
    public void create(OderDetail oderDetail) {
        oderDetailRepository.save(oderDetail);
    }

    @Override
    public void delete(OderDetail oderDetail) {
        oderDetailRepository.delete(oderDetail);
    }

}
