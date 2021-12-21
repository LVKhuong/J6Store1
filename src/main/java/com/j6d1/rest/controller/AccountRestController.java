package com.j6d1.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j6d1.entity.Account;
import com.j6d1.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/account/list/{id}")
	public Account list(@PathVariable("id") String id){
		
		return accountService.findById(id).get();
	}
	
	@GetMapping("/rest/accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	
	
	@PostMapping("/rest/accounts")
	public Account create(@RequestBody Account account) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		account.setPassword(pe.encode(account.getPassword()));
		
		return accountService.save(account);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
