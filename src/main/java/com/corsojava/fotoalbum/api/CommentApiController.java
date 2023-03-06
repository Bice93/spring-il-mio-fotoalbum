package com.corsojava.fotoalbum.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.corsojava.fotoalbum.model.Comment;
import com.corsojava.fotoalbum.model.Photo;
import com.corsojava.fotoalbum.repository.CommentRepository;
import com.corsojava.fotoalbum.repository.PhotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/comments")
public class CommentApiController {
	@Autowired
	private CommentRepository commentR;
	
	@Autowired 
	private PhotoRepository photoR;
	
	@GetMapping
	public List<Comment> index(){
		List<Comment> commentList = commentR.findAll();
		return commentList;
	}
	
	@GetMapping("{id}")
	public List<Comment> commentsById(@PathVariable("id")Long id){
		Photo photo = photoR.getReferenceById(id);
		return photo.getComments();
	}
	
	//CREATE
	@PostMapping("/create/{id}")
	public Comment create(@PathVariable("id") Long id, @RequestBody Comment comment) {
		Photo photo = photoR.getReferenceById(id);
		comment.setPhoto(photo);
		return commentR.save(comment);
		
	}

}
