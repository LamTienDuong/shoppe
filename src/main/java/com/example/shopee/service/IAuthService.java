package com.example.shopee.service;


import com.example.shopee.dto.user.LoginDTO;
import com.example.shopee.dto.user.RegisterDTO;

public interface IAuthService {
    String login(LoginDTO loginDto);

    String register(RegisterDTO registerDto);
}
