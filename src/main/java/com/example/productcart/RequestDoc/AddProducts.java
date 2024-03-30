package com.example.productcart.RequestDoc;


import com.example.productcart.Enums.Available;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProducts {
    private String productName;
    private Available availableStatus;
    private Integer quantity;
    private Integer price;
    private Integer categoryId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Available getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(Available availableStatus) {
        this.availableStatus = availableStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public AddProducts(String productName,Integer quantity, Integer price, Integer categoryId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }
}
