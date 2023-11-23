package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.EncargadoView;
import com.sena.solution.models.Encargado;
import com.sena.solution.services.CuriaService;
import com.sena.solution.services.EncargadoService;

@Controller
@RequestMapping("/encargado")
public class EncargadoController {
	
	@Autowired
	private EncargadoService encargadoService;
	
	@Autowired
	private CuriaService curiaService;
	
	@GetMapping("/home")
	public String index() {
		return EncargadoView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaEncargados() {
		
		ModelAndView modelAndView = new ModelAndView(EncargadoView.LISTE);
		modelAndView.addObject("listaEncargados", encargadoService.listarEncargados());
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearEncargado")
	public ModelAndView formularioCrearEncargado() {
		
		ModelAndView modelAndView = new ModelAndView(EncargadoView.FORME);
		modelAndView.addObject("ObjEncargado", new Encargado());
		modelAndView.addObject("listaCurias", curiaService.listarCurias());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarEncargado")
	public String guardarEncargado(@ModelAttribute("encargado") Encargado encargado) {
		
		encargadoService.guardarEncargado(encargado);
		
		return "redirect:/encargado/listar";
			
	}
		
	@GetMapping("/formularioActualizarEncargado/{idEncargado}")
	public ModelAndView formularioActualizarEncargado(@PathVariable("idEncargado")Long idEncargado) {
		
		ModelAndView modelAndView = new ModelAndView(EncargadoView.FORMUPE);
		modelAndView.addObject("ObjEncargado", encargadoService.buscarPorIdEncargado(idEncargado));
		modelAndView.addObject("listaCurias", curiaService.listarCurias());
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarEncargado")
	public String actualizarEncargado(@ModelAttribute("encargado") Encargado encargado) {
		
		encargadoService.actualizarEncargado(encargado);
		
		return "redirect:/encargado/listar";
			
	}
	
	@GetMapping("/eliminarEncargado/{idEncargado}")
	public String eliminarEncargado(@PathVariable("idEncargado")Long idEncargado) {
		
		encargadoService.eliminarEncargado(encargadoService.buscarPorIdEncargado(idEncargado));
		
		return "redirect:/encargado/listar";
	}

	
}
