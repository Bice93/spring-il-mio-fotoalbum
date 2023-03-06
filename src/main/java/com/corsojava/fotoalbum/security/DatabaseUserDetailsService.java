package com.corsojava.fotoalbum.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.corsojava.fotoalbum.model.User;
import com.corsojava.fotoalbum.repository.UserRepository;

public class DatabaseUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userR;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userR.findByUsername(username);
		if (user.isPresent()) {
			return new DatabaseUserDetails(user.get());
		} else throw new UsernameNotFoundException("Utente non trovato!");
	}

}
