package com.app.ecommerce.shopping.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//---------------------------------------------

import com.app.ecommerce.shopping.model.ConfirmOrder;
//--------------------------------------------

@Repository
public interface ConfirmSubmitRepository extends JpaRepository<ConfirmOrder, Long> {
	
	List<ConfirmOrder> findByUserEmail(String userEmail);
}
