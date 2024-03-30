package com.example.productcart.Repository;

import com.example.productcart.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, String> {
}
