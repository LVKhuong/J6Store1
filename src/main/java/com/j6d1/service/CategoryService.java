package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.j6d1.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	void deleteById(Integer id);

	Optional<Category> findById(Integer id);

	Category insert(Category category);

	Category update(Category category);
	

}
