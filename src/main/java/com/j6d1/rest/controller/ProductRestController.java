package com.j6d1.rest.controller;

import java.util.List;

import com.j6d1.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.j6d1.entity.Product;
import com.j6d1.exception.NotFoundException;
import com.j6d1.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService productService;

//	@PreAuthorize("hasRole('/rest/products/one')")
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id)
				.orElseThrow(() -> new NotFoundException("Sản phẩm không tìm thấy với id = " + id));
	}
	
//	@PreAuthorize("hasRole('/rest/products/all')")
	@GetMapping("")
	public List<Product> getAll() {
		if(productService.findAll().isEmpty()){
			throw new NotFoundException("Không có sản phẩm nào !");
		}else{
			return productService.findAll();
		}
	}

//	@PreAuthorize("hasRole('/rest/products/create')")
	@PostMapping("")
	public Product create(@RequestBody Product product) {
		if(productService.findById(product.getId()).isPresent()){
			throw new BadRequestException("Sản phẩm đã tồn tại trong dữ liệu!");
		}else{
			return productService.create(product);
		}
	}

//	@PreAuthorize("hasRole('/rest/products/update')")
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		if (!productService.existsById(product.getId())) {
			throw new NotFoundException("Sản phẩm không tìm thấy với id = " + product.getId());
		}else{
			return productService.update(product);
		}

	}

//	@PreAuthorize("hasRole('/rest/products/delete')")
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		if (!productService.existsById(id)) {
			throw new NotFoundException("Sản phẩm không tìm thấy với id = " + id);
		}else{
			productService.delete(id);
		}
	}

//	@GetMapping("/search")
//	public Page<Product> search(@PathParam("search") Optional<String> search,
//			@PathParam("p") Optional<Integer> page) {
//
//		return productService.search(search.get(), PageRequest.of(page.orElse(0), 6));
//	}
	
	@GetMapping("/selectAll")
	public List<Product> selectAll(){
		if(productService.selectAll().isEmpty()){
			throw new NotFoundException("Không có sản phẩm nào !");
		}else{
			return productService.selectAll();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
