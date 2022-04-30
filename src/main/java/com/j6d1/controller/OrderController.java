package com.j6d1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.j6d1.service.OrderDetailService;
import com.j6d1.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderService orderService;

	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		
		return "order/list";
	}
	
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findById(id).get());
		
		return "order/detail";
	}
	
	
	
	
	
	
	
	
	
	
	
}