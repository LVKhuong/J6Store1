package com.j6d1.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j6d1.entity.Authority;
import com.j6d1.service.AccountService;
import com.j6d1.service.AuthorityService;
import com.j6d1.service.RoleService;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthorityService authorityService;
	
	
	
	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(@RequestParam("admin") Optional<Boolean> admin){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityService.findAll());
		data.put("roles", roleService.findAll());
		data.put("account", accountService.findAll());
		
		return data;
	}
	
	
	
	@GetMapping("/rest/authoritiesAdmin")
	public List<Authority> getAll(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdmin();
		}
		
		return authorityService.findAll();
	}
	
	
	
	
	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authority) {
		return authorityService.save(authority);
	}
	
	
	
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	

}
