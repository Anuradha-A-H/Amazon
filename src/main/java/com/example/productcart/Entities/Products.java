package com.example.productcart.Entities;

import com.example.productcart.Enums.Available;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "varchar(255) default 'AVAILABLE'")

    private Available availableStatus = Available.AVAILABLE;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Override
    public String toString() {
        return "Products{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", availableStatus=" + availableStatus +
                ", category=" + category +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @JoinColumn
    @ManyToOne
    private Category category;

    public Products(String productName, Integer quantity,Integer price,Category category)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @JoinColumn
    @ManyToOne
    private Orders orders;
}
