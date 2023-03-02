package com.corsojava.fotoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.fotoalbum.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	//ha già tutti metodi per eseguire le operazioni CRUD
}
