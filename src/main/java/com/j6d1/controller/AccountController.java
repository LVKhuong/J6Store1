package com.j6d1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@GetMapping("/register")
	public String register() {
		return "security/register-user";
	}
	
	
	
	
	
	
	
	
	
	
	
}
