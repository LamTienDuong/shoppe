package com.example.shopee.service.impl;

import com.example.shopee.entity.Oder;
import com.example.shopee.repository.IOderRepository;
import com.example.shopee.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderService implements IOderService {

    @Autowired
    private IOderRepository oderRepository;

    @Override
    public Integer getMonthlySalesCount(int year, int month) {
        return oderRepository.getMonthlySalesCount(year, month);
    }

    @Override
    public List<Oder> findByUserId(int id) {
        return oderRepository.findByUserId(id);
    }

    @Override
    public void create(Oder oder) {
        oderRepository.save(oder);
    }

}
