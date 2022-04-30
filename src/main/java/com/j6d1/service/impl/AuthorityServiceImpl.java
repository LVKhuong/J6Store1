package com.j6d1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j6d1.dao.AccountDAO;
import com.j6d1.dao.AuthorityDAO;
import com.j6d1.entity.Account;
import com.j6d1.entity.Authority;
import com.j6d1.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;

	@Autowired
	AccountDAO accDao;
	
	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Authority> findById(Integer id) {
		return dao.findById(id);
	}

	public Authority save(Authority entity) {
		return dao.save(entity);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdmin() {
		List<Account> accounts = accDao.getAdministrators();
		return dao.authoritiesOf(accounts);
	}
	
	
	
	
}
