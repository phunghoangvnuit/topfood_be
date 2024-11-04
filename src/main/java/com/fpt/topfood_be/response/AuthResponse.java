package com.fpt.topfood_be.response;

import com.fpt.topfood_be.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;

    private USER_ROLE role;
}
