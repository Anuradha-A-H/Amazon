package com.example.productcart.Entities;

import com.example.productcart.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;


    private Integer UserId;


    private Status status;
    private String address;


    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Products> productsList = new ArrayList<>();

//    @ManyToOne
//    private User deliveryBoy;

}
