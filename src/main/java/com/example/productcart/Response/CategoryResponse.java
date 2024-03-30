package com.example.productcart.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CategoryResponse {

    private Integer categoryId;
    private String categoryName;
    List<String> productsList;
}
