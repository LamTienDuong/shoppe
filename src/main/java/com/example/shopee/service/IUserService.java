package com.example.shopee.service;

import com.example.shopee.entity.User;

import java.util.Optional;

public interface IUserService {
    User findById(int id);

    Optional<User> findByEmail(String email);
}
