package com.example.productcart.RequestDoc;


import com.example.productcart.Enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String address;
    private Role role;
}
