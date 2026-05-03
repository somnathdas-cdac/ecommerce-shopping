package com.app.ecommerce.shopping.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//-----------------------------------------------------------------------

import com.app.ecommerce.shopping.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email , String password);
}
