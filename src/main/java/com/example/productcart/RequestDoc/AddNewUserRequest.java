package com.example.productcart.RequestDoc;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewUserRequest {

    private String firstName;
    private String lastName;

    private Date dob;

    private String address;
    private String username;
    private String password;
}
