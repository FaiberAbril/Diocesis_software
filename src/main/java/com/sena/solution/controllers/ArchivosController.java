package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sena.solution.controllers.views.ArchivoView;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ArchivoCategoriaGeneralService;
import com.sena.solution.services.UsuarioService;


@Controller
@RequestMapping("/archivo")
public class ArchivosController {
	
	@Autowired
	private ArchivoCategoriaGeneralService aCGService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String index() {
		return ArchivoView.HOME;
	}


	@GetMapping("/archivosACG")
	public ModelAndView mostrarACG(){
		ModelAndView modelAndView = new ModelAndView(ArchivoView.HOME);
		Usuario usuario = usuarioService.buscarPorIdUsuario(Long.valueOf(1));
		modelAndView.addObject("listaACG", aCGService.buscarPorParroquia(usuario.getParroquia()));
		
		return modelAndView;
	}


}
