package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.ArchivoCategoriaGeneralView;
import com.sena.solution.models.ArchivoCategoriaGeneral;
import com.sena.solution.services.ArchivoCategoriaGeneralService;

@Controller
@RequestMapping("/acg")
public class ArchivosCategoriaGeneral {
	
	@Autowired
	private ArchivoCategoriaGeneralService aCGService;
	
	@GetMapping("/home")
	public String index() {
		return ArchivoCategoriaGeneralView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaACG() {
		
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.LISTC);
		modelAndView.addObject("listaACG", aCGService.listarACG());
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearACG")
	public ModelAndView formularioCrearACG() {
		
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.FORMC);
		modelAndView.addObject("objACG", new ArchivosCategoriaGeneral());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarACG")
	public String guardarACG(@ModelAttribute("ACG") ArchivoCategoriaGeneral aCG) {
		
		aCGService.guardarACG(aCG);
		
		return "redirect:/acg/listar";
			
	}
		
	@GetMapping("/formularioActualizarACG/{idACG}")
	public ModelAndView formularioActualizarCuria(@PathVariable("idACG")Long idACG) {
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.FORMUPC);
		modelAndView.addObject("objACG", aCGService.buscarPorIdACG(idACG));
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarACG")
	public String actualizarACG(@ModelAttribute("ACG") ArchivoCategoriaGeneral aCG) {
		
		aCGService.actualizarACG(aCG);
		
		return "redirect:/acg/listar";
			
	}
	
	@GetMapping("/eliminarACG/{idACG}")
	public String eliminarCuria(@PathVariable("idACG")Long idACG) {
		
		aCGService.eliminarACG(aCGService.buscarPorIdACG(idACG));
		
		return "redirect:/acg/listar";
	}
}
