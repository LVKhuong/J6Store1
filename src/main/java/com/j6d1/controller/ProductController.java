package com.j6d1.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.j6d1.dao.ProductDAO;
import com.j6d1.entity.Product;
import com.j6d1.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductDAO dao;

//	@RequestMapping("/product/list")
//	public String list(Model model, @PathParam("cid") Optional<Integer> cid) {
//		if(cid.isPresent()) {
////			List<Product> list = productService.findByCategoryId(cid.get());
////			model.addAttribute("ITEMS", list);
//		}else {
//			List<Product> list = dao.findAll();
//			model.addAttribute("ITEMS", list);
//		}
//		
//		return "product/list";
//	}

	@RequestMapping({ "/product/page", "/product/page/{cID}"})
	public String paginate(Model model, @PathParam("p") Optional<Integer> p, 
			@PathVariable("cID") Optional<Integer> cID, @PathParam("search") Optional<String> search) {		
		//
		Pageable pageable = PageRequest.of(p.orElse(0), 3, Sort.by(Sort.Direction.DESC, "price"));
		Page<Product> page;
		if (cID.isPresent()) {
			page = productService.findByCategoryId(cID.get(), pageable);
		} else if(search.isPresent()) {
			page = productService.search(search.get(), pageable);
		} else {
			page = productService.findAll(pageable);	
		}	
		model.addAttribute("ITEMS_PAGE", page);
		
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id).get();
		model.addAttribute("ITEM", item);

		return "product/detail";
	}
}
