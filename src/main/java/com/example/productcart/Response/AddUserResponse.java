package com.example.productcart.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {

    private Integer userId;
    private String username;
    private String password;
    private String message;
}
