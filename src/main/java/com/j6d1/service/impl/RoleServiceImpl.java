package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6d1.dao.RoleDAO;
import com.j6d1.entity.Role;
import com.j6d1.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Role> findById(String id) {
		return dao.findById(id);
	}
	
	
}
