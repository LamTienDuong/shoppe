package com.example.shopee.repository;

import com.example.shopee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer>  {
    @Query("SELECT c.name FROM Category c")
    List<String> findAllNames();
}
