package com.j6d1.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.j6d1.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT p "
		+ "FROM Product p "
		+ "WHERE p.category.id = ?1")
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);
	
	
	
//	@Query("SELECT p "
//		+ "FROM Product p "
//		+ "WHERE p.name LIKE ?1")
//	@Query("SELECT p "
//		+ "FROM Product p "
//		+ "WHERE CONCAT(p.name) LIKE %?1%")
	@Query(value = "SELECT * "
				+ "FROM products "
				+ "WHERE FREETEXT(name, :name)", 
			nativeQuery = true)
	Page<Product> searchProduct(@Param("name") String name, Pageable pageable);
	
	
	
	@Query("SELECT p "
		+ "FROM Product p "
		+ "INNER JOIN Category c ON c.id = p.category.id "
		+ "INNER JOIN OrderDetail o ON o.product.id = p.id")
	List<Product> selectAll();
	
	
	
	
	
	

}
 