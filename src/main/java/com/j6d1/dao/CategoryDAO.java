package com.j6d1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6d1.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
