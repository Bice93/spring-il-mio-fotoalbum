package com.corsojava.fotoalbum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.fotoalbum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
