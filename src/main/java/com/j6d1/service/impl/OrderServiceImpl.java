package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j6d1.dao.OrderDAO;
import com.j6d1.entity.Order;
import com.j6d1.entity.OrderDetail;
import com.j6d1.service.OrderDetailService;
import com.j6d1.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailService orderDetailService;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream()
				.peek(d -> d.setOrder(order))
				.collect(Collectors.toList());
		
		orderDetailService.saveAll(details);
		
		return order;
	}

	@Override
	public Order save(Order entity) {
		return dao.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<Order> findByUsername(String username) {
		
		return dao.findByUsername(username);
	}
	
	
}
