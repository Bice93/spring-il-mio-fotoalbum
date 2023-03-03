package com.corsojava.fotoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping()
	public String index() {
		return "/front-end/pages/index";
	}
	
	@GetMapping("/show")
	public String show() {
		return "/front-end/pages/show";
	}
	
}
