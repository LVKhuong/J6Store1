package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j6d1.dao.ProductDAO;
import com.j6d1.entity.Product;
import com.j6d1.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Lazy
	ProductDAO dao;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {

		return dao.findByCategoryId(cid, pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Product create(Product product) {

		return dao.save(product);
	}

	@Override
	public Product update(Product product) {

		return dao.save(product);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public Page<Product> search(String search, Pageable pageable) {
		return dao.searchProduct(search, pageable);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}
	
	
	@Override
	public List<Product> selectAll(){
		return dao.selectAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
