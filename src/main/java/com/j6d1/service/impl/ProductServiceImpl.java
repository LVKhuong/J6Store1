package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.j6d1.dao.ProductDAO;
import com.j6d1.entity.Product;
import com.j6d1.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
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
	public Page<Product> findByCategoryId(Integer cid,Pageable pageable) {

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

}
