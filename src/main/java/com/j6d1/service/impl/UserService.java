package com.j6d1.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.j6d1.entity.Account;
import com.j6d1.service.AccountService;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Account account = accountService.findById(username).get();
			String pass = account.getPassword();
			String roles[] = account.getAuthorities().stream()
					.map(au -> au.getRole().getId())
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			UserDetails user = User.withUsername(username)
					.password(new BCryptPasswordEncoder().encode(pass))
					.roles(roles)
					.build();
			
			return user;
			
//			return new UserDetailsPrincipal(account);

	}

	
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String username = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails user = User.withUsername(username)
				.password(new BCryptPasswordEncoder().encode(password))
				.roles("USER")
				.build();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
