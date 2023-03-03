package com.corsojava.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.fotoalbum.model.Category;
import com.corsojava.fotoalbum.model.Photo;
import com.corsojava.fotoalbum.repository.CategoryRepository;
import com.corsojava.fotoalbum.repository.PhotoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	private @Autowired PhotoRepository photoR;
	private @Autowired CategoryRepository categoryR;

	@GetMapping
	public String index(@RequestParam(name = "search", required = false) String keyword, Model model) {
		List<Photo> photoList;
		if (keyword != null && !keyword.isEmpty()) {
			photoList = photoR.findByTitleLikeOrTagLike("%" + keyword + "%", "%" + keyword + "%");
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

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("photo", new Photo());
		
		List<Category> categoryList = categoryR.findAll();
		model.addAttribute("categories", categoryList);
		
		return "/photos/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingR, Model model) {
		
		if (bindingR.hasErrors()) {
			model.addAttribute("categories", categoryR.findAll());
			return "/photos/create";
		}
		
		photoR.save(formPhoto);			
		return "redirect:/photos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("photo", photoR.getReferenceById(id));
		
		List<Category> categoryList = categoryR.findAll();
		model.addAttribute("categories", categoryList);
		
		return "/photos/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bR, Model model) {
		
		if (bR.hasErrors()) {
			model.addAttribute("categories", categoryR.findAll());
			return "photos/edit";
		}
		
		photoR.save(formPhoto);
		return "redirect:/photos/"+formPhoto.getId();
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		photoR.deleteById(id);
		
		return "redirect:/photos";
	}
}
