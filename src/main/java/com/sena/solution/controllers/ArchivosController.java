package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.sena.solution.controllers.views.ArchivoView;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ArchivoCategoriaGeneralService;
import com.sena.solution.services.UsuarioService;

import jakarta.websocket.server.PathParam;


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


	@GetMapping("/archivosACG/{idUsuario}")
	public ModelAndView mostrarACG(@PathVariable("idUsuario")Long idUsuario){
		
		ModelAndView modelAndView = new ModelAndView(ArchivoView.HOME);
		Usuario usuario = usuarioService.buscarPorIdUsuario(idUsuario);
		modelAndView.addObject("listaACG", aCGService.buscarPorParroquia(usuario.getParroquia()));
		
		return modelAndView;
	}
	
	
	@GetMapping("/crearArchivo/{idParroquia}/{idACG}")
	public ModelAndView crearArchivoACG(){
		ModelAndView modelAndView = new ModelAndView("listarArchivos");
		
		
		
		return modelAndView;
	}
	
	@GetMapping("/listarArchivos/{idACG}")
	public ModelAndView listarArchivos() {
		ModelAndView modelAndView = new ModelAndView(ArchivoView.LISTA);
		return modelAndView;
	}


}
