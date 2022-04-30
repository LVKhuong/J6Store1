package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6d1.dao.OrderDetailDAO;
import com.j6d1.entity.OrderDetail;
import com.j6d1.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailDAO dao;

	@Override
	public OrderDetail save(OrderDetail entity) {
		return dao.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<OrderDetail> saveAll(List<OrderDetail> entities) {
		return dao.saveAll(entities);
	}
	
	
}
