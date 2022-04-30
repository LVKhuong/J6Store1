package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.j6d1.entity.OrderDetail;

public interface OrderDetailService {

	Optional<OrderDetail> findById(Integer id);

	List<OrderDetail> findAll();

	OrderDetail save(OrderDetail entity);

	List<OrderDetail> saveAll(List<OrderDetail> entities);

}
