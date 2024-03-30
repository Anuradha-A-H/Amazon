package com.example.productcart.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String token;
}
