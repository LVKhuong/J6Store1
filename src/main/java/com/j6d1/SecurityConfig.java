package com.j6d1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.j6d1.service.AccountService;
import com.j6d1.service.impl.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;	

	@Bean
	public PasswordEncoder getPasswordEncode() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.authenticationProvider(authenticationProvider());

//		auth.userDetailsService(username -> {
//			try {
//				Account user = accountService.findById("user1").get();
//				String password = user.getPassword();
//				String roles[] = user.getAuthorities().stream()
//						.map(er -> er.getRole().getId())
//						.collect(Collectors.toList())
//						.toArray(new String[0]);
//				
//				return User.withUsername(username)
//						.password(password)
//						.roles(roles)
//						.build();
//				
//			} catch (NoSuchElementException e) {
//				throw new UsernameNotFoundException(username + "not found!");
//			}
//		});

		// auth.userDetailsService(userService).passwordEncoder(getPasswordEncode());

		auth.inMemoryAuthentication()
			.withUser("user1").password(this.getPasswordEncode().encode("123")).roles("ADMIN")
			.and()
			.withUser("user2").password(this.getPasswordEncode().encode("123")).roles("STAF")
			.and()
			.withUser("user3").password(this.getPasswordEncode().encode("123")).roles("DIRE");
		
		
		
		
		
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();

		http.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/rest/authorities", "/rest/authorities/**","/rest/authoritiesAdmin").hasRole("ADMIN")
		.antMatchers("/assets/admin", "/assets/admin/**").hasAnyRole("STAF", "DIRE","ADMIN"	)
		.anyRequest().permitAll();

		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success", false)
			.failureUrl("/security/login/error");

//		http.httpBasic();

		http.rememberMe()
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(86400);

		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried");

		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/success");

		// Social Facebook, Google
		http.oauth2Login()
			.loginPage("/security/login/form")
			.defaultSuccessUrl("/oauth2/login/success", true)
			.failureUrl("/security/login/error")
			// set up url login social
			.authorizationEndpoint()
				.baseUri("/oauth2/authorization");
		

	}

}
