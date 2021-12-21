package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6d1.dao.CategoryDAO;
import com.j6d1.entity.Category;
import com.j6d1.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO dao;

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public Category insert(Category entity) {
		return dao.save(entity);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Category update(Category category) {
		return dao.save(category);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
