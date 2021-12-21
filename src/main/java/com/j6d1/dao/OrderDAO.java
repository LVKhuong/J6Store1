package com.j6d1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.j6d1.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);

}
