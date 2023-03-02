package com.corsojava.fotoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.fotoalbum.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	//ha già tutti metodi per eseguire le operazioni CRUD
}
