package com.corsojava.fotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
		.and().logout()
		.and().exceptionHandling()
		.accessDeniedPage("/access-denied.html");
		
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
