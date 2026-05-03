package com.app.ecommerce.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//-----------------------------------------------------

import com.app.ecommerce.shopping.model.Order;



public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
