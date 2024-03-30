package com.example.productcart.RequestDoc;

import lombok.Data;

@Data
public class UpdateLoginRequest {

    private String userName;
    private String oldPassword;
    private String newpassword;
}
