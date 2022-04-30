package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.j6d1.entity.Account;

public interface AccountService {

	List<Account> findAll();

	Optional<Account> findById(String id);

	List<Account> getAdministrators();

	Account save(Account entity);
	
	

}
