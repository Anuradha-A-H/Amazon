package com.example.productcart.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "logins")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginId;

    @Column(unique = true)
    private String userName;


    private String password;


    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private User user;

}
