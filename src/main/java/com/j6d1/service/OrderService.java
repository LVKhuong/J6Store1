package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.j6d1.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Optional<Order> findById(Integer id);

	List<Order> findAll();

	Order save(Order entity);

	List<Order> findByUsername(String username);

}
