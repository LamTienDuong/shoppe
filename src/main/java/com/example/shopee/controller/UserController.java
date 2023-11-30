package com.example.shopee.controller;

import com.example.shopee.entity.User;
import com.example.shopee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://127.0.0.1:5500/")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public ResponseEntity<Integer> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin người dùng từ principal
            String email = authentication.getName();
            // Tìm kiếm thông tin nguoi dùng thông qua email
            Optional<User> user = userService.findByEmail(email);

            return new ResponseEntity<>(user.orElse(new User()).getId(), HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin người dùng từ principal
            String email = authentication.getName();
            // Tìm kiếm thông tin nguoi dùng thông qua email
            Optional<User> user = userService.findByEmail(email);

            return new ResponseEntity<>(user.orElse(new User()).getUsername(), HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
