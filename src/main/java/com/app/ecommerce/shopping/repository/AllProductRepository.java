package com.app.ecommerce.shopping.repository;




import org.springframework.data.jpa.repository.JpaRepository;


import com.app.ecommerce.shopping.model.UploadProduct;

public interface AllProductRepository extends JpaRepository<UploadProduct, Long> {
	
	
}
