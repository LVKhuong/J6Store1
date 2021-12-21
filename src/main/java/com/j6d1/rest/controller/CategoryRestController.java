package com.j6d1.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.j6d1.entity.Category;
import com.j6d1.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("")
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("{id}")
	public Category getOne(@PathVariable("id") Integer id){
		return categoryService.findById(id).get();
	}
	
	@PostMapping("")
	public Category insert(@RequestBody Category category) {
		return categoryService.insert(category);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		categoryService.deleteById(id);
	}
	
	@PutMapping("{id}")
	public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
		
		return categoryService.update(category);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
