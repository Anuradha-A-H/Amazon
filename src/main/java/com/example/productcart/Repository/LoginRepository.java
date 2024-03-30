package com.example.productcart.Repository;

import com.example.productcart.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findLoginByUserName(String name);



//    public Login findLoginByEmailand(String username, String password);
//    public Login findLoginByUserName(String username)

}
