package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.ArchivoView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.services.ParroquiaAcgService;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.UsuarioService;



@Controller
@RequestMapping("/archivo")
public class ArchivosController {
	
	@Autowired
	private ParroquiaAcgService parroquiaAcgService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ParroquiaService parroquiaService;
	
	@GetMapping("/")
	public String index() {
		return ArchivoView.HOME;
	}


	@GetMapping("/parroquiaAcg/{idParroquia}")
	public ModelAndView mostrarACG(@PathVariable("idParroquia")Long idParroquia){
		
		ModelAndView modelAndView = new ModelAndView(ArchivoView.HOME);
		Parroquia parroquia = parroquiaService.buscarPorIdParroquia(idParroquia);
		modelAndView.addObject("listaParroquiaACG", parroquiaAcgService.buscarPorParroquia(parroquia));
		
		return modelAndView;
	}
	
	
	@GetMapping("/listar")
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
