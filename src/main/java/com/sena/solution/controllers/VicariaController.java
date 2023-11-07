package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.VicariaView;
import com.sena.solution.models.Vicaria;
import com.sena.solution.services.CuriaService;
import com.sena.solution.services.VicariaService;

@Controller
@RequestMapping("/vicaria")
public class VicariaController {
	
	
	@Autowired
	private VicariaService vicariaService;
	
	@Autowired
	private CuriaService curiaService;
	
	@GetMapping("/home")
	public String index() {
		return VicariaView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaVicaria() {
		
		ModelAndView modelAndView = new ModelAndView(VicariaView.LISTV);
		modelAndView.addObject("listaVicarias", vicariaService.listarVicarias());
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearVicarias")
	public ModelAndView formularioCrearVicarias() {
		
		ModelAndView modelAndView = new ModelAndView(VicariaView.FORMV);
		modelAndView.addObject("objVicaria", new Vicaria());
		modelAndView.addObject("listaCurias", curiaService.listarCurias());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarVicaria")
	public String guardarVicaria(@ModelAttribute("vicaria")Vicaria vicaria) {
		
		vicariaService.guardarVicaria(vicaria);
		
		return "redirect:/vicaria/listar";
			
	}
		
	@GetMapping("/formularioActualizarVicaria/{idVicaria}")
	public ModelAndView formularioActualizarVicaria(@PathVariable("idVicaria")Long idVicaria) {
		
		ModelAndView modelAndView = new ModelAndView(VicariaView.FORMUPV);
		modelAndView.addObject("objVicaria", vicariaService.buscarPorIdVicaria(idVicaria));
		modelAndView.addObject("listaCurias", curiaService.listarCurias());
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarVicaria")
	public String actualizarVicaria(@ModelAttribute("vicaria") Vicaria vicaria) {
		
		vicariaService.actualizarVicaria(vicaria);
		
		return "redirect:/vicaria/listar";
			
	}
	
	@GetMapping("/eliminarVicaria/{idVicaria}")
	public String eliminarVicaria(@PathVariable("idVicaria")Long idVicaria) {
		
		vicariaService.eliminarCuria(vicariaService.buscarPorIdVicaria(idVicaria));
		
		return "redirect:/vicaria/listar";
	}

	

}
