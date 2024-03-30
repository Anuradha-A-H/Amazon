package com.example.productcart.Response;

import com.example.productcart.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String orderId;
    private Status status;
    private List<String> productsList;
    private String message;
    private String address;


}
