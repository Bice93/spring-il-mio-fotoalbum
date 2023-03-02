package com.corsojava.fotoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.fotoalbum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	//ha già tutti metodi per eseguire le operazioni CRUD
}
