package com.corsojava.fotoalbum.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		.requestMatchers("/photos", "/photos/**").hasAuthority("ADMIN")
		.requestMatchers("/photos/edit", "/photos/edit/**", "/photo/create", "/photo/create/**").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")
		.requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")
		.requestMatchers("/categories/edit", "/categories/edit/**", "/categories/create", "/categories/create/**").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "*/categories/**").hasAuthority("ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin()
		.loginPage("/login").permitAll()
		.and().logout()
		.logoutSuccessUrl("/")
		.and().exceptionHandling()
		.accessDeniedPage("/access-denied.html")
		.and().csrf().disable();
		
		return http.build();
	}
	
	@Bean
	DatabaseUserDetailsService userDetailService() {
		return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		//System.out.println(passwordEncoder().encode("root"));
		
		return authProvider;
		
	}
}
