package com.j6d1.rest.controller;

import java.util.List;

import com.j6d1.exception.BadRequestException;
import com.j6d1.exception.NotFoundException;
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
		if(categoryService.findAll().isEmpty()){
			throw new BadRequestException("Không có sản phẩm nào !");
		}else {
			return categoryService.findAll();
		}
	}
	
	@GetMapping("{id}")
	public Category getOne(@PathVariable("id") Integer id){
		if(categoryService.findById(id).isPresent()){
			return categoryService.findById(id).get();
		}else{
			throw new NotFoundException("Sản phẩm không được tìm thấy với id = " + id);
		}
	}
	
	@PostMapping("")
	public Category insert(@RequestBody Category category) {
		if(categoryService.findById(category.getId()).isPresent()){
			throw new BadRequestException("Sản phẩm đã có trong dữ liệu");
		}else {
			return categoryService.insert(category);
		}
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		if(categoryService.findById(id).isPresent()){
			categoryService.deleteById(id);
		}else{
			throw new NotFoundException("Sản phẩm không tồn tại");
		}
	}
	
	@PutMapping("{id}")
	public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
		if (categoryService.findById(category.getId()).isPresent()){
			return categoryService.update(category);
		}else{
			throw new BadRequestException("Sản phẩm không tồn tại");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
