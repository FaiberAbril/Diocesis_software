package com.sena.solution.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.solution.controllers.views.ArchivoView;


@Controller
@RequestMapping("/archivo")
public class ArchivosController {
	
	
	@GetMapping("/")
	public String index() {
		return ArchivoView.HOME;
	}
}
