package com.example.shopee.repository;

import com.example.shopee.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderRepository extends JpaRepository<Oder, Integer> {

    @Query(value =  "select SUM(od.quantity)\n" +
                    "from oder o inner join oder_detail od on o.id = od.oder_id\n" +
                    "where (:year = '' or :year is null or YEAR(o.oder_date) = :year)" +
                    "and (:month = '' or :month is null or  MONTH(o.oder_date) = :month)", nativeQuery = true)
    Integer getMonthlySalesCount(@Param("year") int year, @Param("month") int month);
}
