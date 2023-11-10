package com.example.shopee.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MessageErrorDTO {
    private String filed;
    private String message;
}
