package com.example.productcart.Repository;

import com.example.productcart.Entities.Login;
import com.example.productcart.Entities.User;
import com.example.productcart.Enums.Role;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String  str);
    User findByRole(Role role);
    Optional<User> findByLogin(Login logins);
}
