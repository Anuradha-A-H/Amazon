package com.example.productcart.RequestDoc;

import com.example.productcart.Entities.Products;
import com.example.productcart.Enums.Status;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {
    private Integer userId;


    private Status status = Status.ORDER_PLACED;
    private String address;

    private List<String> productsList = new ArrayList<>();
}
