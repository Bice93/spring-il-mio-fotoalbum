package com.corsojava.fotoalbum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.fotoalbum.model.Category;
import com.corsojava.fotoalbum.repository.CategoryRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryR;
	
	@GetMapping
	public String index(Model model) {
		List<Category> categoryList = categoryR.findAll();
		model.addAttribute("categories", categoryList);
		return "/categories/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("category", new Category());
		return "categories/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingR, Model model) {
		
		if (bindingR.hasErrors()) {
			return "/categories/create";
		}
		
		categoryR.save(formCategory);
		return "redirect:/categories";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("category", categoryR.getReferenceById(id));
		
		return "/categories/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute Category formCategory, BindingResult bindingR, Model model) {
		if (bindingR.hasErrors()) {
			return "/categories/edit";
		}
		categoryR.save(formCategory);
		return "redirect:/categories";
	}
	
	@PostMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		categoryR.deleteById(id);
		return "redirect:/categories";
	}
}
