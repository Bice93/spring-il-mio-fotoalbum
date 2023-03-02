package com.corsojava.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.fotoalbum.model.Photo;
import com.corsojava.fotoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/photos")
public class PhotoController {
	
	private @Autowired PhotoRepository photoR;
	
	@GetMapping
	public String index(@RequestParam(name="search", required = false) String keyword, Model model) {
		List<Photo> photoList;
		if(keyword != null && !keyword.isEmpty()) {
			photoList = photoR.findByTitleLikeOrTagLike("%"+keyword+"%", "%"+keyword+"%");
		} else {
			photoList = photoR.findAll();
		}
		model.addAttribute("photos", photoList);
		
		return "/photos/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Photo> photo = photoR.findById(id);
		model.addAttribute("photo", photo.get());
		
		return "/photos/show";
	}
}
