package com.example.shopee.service.impl;

import com.example.shopee.entity.User;
import com.example.shopee.repository.IUserRepository;
import com.example.shopee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
