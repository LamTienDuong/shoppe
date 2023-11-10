package com.example.shopee.repository;

import com.example.shopee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ICategoryRepository extends JpaRepository<Category, Integer>  {
}
