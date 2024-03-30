package com.example.productcart.Response;

import com.example.productcart.Enums.Available;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AllProductsResponse {


    private String productId;


    private String productName;


    private Available availableStatus;


    private Integer quantity;


    private Integer price;

    private String category;

}
