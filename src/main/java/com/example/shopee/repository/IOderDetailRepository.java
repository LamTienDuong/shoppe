package com.example.shopee.repository;

import com.example.shopee.entity.OderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOderDetailRepository extends JpaRepository<OderDetail, Integer> {

    @Query(value = "from OderDetail od where od.oder.id = :id")
    List<OderDetail> findByOderId(int id);

}
