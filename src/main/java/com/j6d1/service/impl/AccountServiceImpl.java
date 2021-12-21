package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6d1.dao.AccountDAO;
import com.j6d1.entity.Account;
import com.j6d1.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired AccountDAO dao;

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Account> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<Account> getAdministrators() {
		
		return dao.getAdministrators();
	}

	@Override
	public Account save(Account entity) {
		
		
		return dao.save(entity);
	}
	
	
	
	
}
