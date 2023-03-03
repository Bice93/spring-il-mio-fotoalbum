package com.corsojava.fotoalbum.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.corsojava.fotoalbum.model.Photo;
import com.corsojava.fotoalbum.repository.PhotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoApiController {

	@Autowired
	private PhotoRepository photoR;
	
	@GetMapping
	public List<Photo> index(@RequestParam(name="search", required = false) String keyword){
		List<Photo> photoList;
		if (keyword != null && !keyword.isEmpty()) {
			photoList = photoR.findByTitleLikeOrTagLike("%" + keyword + "%", "%" + keyword + "%");
		} else {
			photoList = photoR.findAll();
		}
		return photoList;
	}
}
