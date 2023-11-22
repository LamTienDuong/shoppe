package com.example.shopee.controller;

import com.example.shopee.entity.Oder;
import com.example.shopee.entity.OderDetail;
import com.example.shopee.service.IOderDetailService;
import com.example.shopee.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("oders")
@CrossOrigin("http://127.0.0.1:5500/")
public class OderController {
    @Autowired
    private IOderDetailService oderDetailService;

    @Autowired
    private IOderService oderService;

    @GetMapping("")
    public ResponseEntity<List<Integer>> getMonthlySales(@RequestParam("year") int year) {
        List<Integer> monthlySales = new ArrayList<>();
        // Thực hiện truy vấn để lấy số lượng sản phẩm đã bán cho từng tháng trong năm
        for (int month = 1; month <= 12; month++) {
            Integer salesForMonth = oderService.getMonthlySalesCount(year, month);
            monthlySales.add(salesForMonth);
        }

        return new ResponseEntity<>(monthlySales, HttpStatus.OK);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Oder oder = oderService.findById(id);
        List<OderDetail> oderDetailList = oderDetailService.findByOderId(oder.getId());
        for (OderDetail oderDetail : oderDetailList) {
            oderDetailService.delete(oderDetail);
        }
        oderService.delete(oder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("{id}")
    public ResponseEntity<List<OderDetail>> getOder(@PathVariable("id") int id) {
        Oder oder = oderService.findById(id);
        List<OderDetail> oderDetailList = oderDetailService.findByOderId(oder.getId());

        return new ResponseEntity<>(oderDetailList,HttpStatus.OK);
    }

}
