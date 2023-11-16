package com.example.shopee.repository;

import com.example.shopee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer>  {

    @Query(value = "select  SUM(p.quantity), c.id, c.name\n" +
                    "from product p inner join category c on p.category_id = c.id\n" +
                    "group by c.id, c.name", nativeQuery = true)
    List<Object[]> getQuantityByCategory();
    @Query("SELECT c.name FROM Category c")
    List<String> findAllNames();
}
