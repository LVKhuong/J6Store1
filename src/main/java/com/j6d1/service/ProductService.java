package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.j6d1.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Optional<Product> findById(Integer id);

	Page<Product> findByCategoryId(Integer cid, Pageable pageable);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	Page<Product> findAll(Pageable pageable);

	Page<Product> search(String search, Pageable pageable);

	

}
