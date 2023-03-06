package com.corsojava.fotoalbum.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.corsojava.fotoalbum.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	//ha già tutti metodi per eseguire le operazioni CRUD
	public List<Photo> findByTitleLikeOrTagLike(String title, String tag);
	public List<Photo> findByIsVisibleTrue();
	//public List<Photo> findByTitleLikeOrTagLikeAndIsVisibleTrue(String title, String tag);
	public List<Photo> findByTitleLikeAndIsVisibleTrueOrTagLikeAndIsVisibleTrue(String title, String tag);
}
