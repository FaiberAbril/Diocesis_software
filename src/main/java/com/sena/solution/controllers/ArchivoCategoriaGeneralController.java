package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.sena.solution.services.ParroquiaService;

@Controller
@RequestMapping("/acg")
public class ArchivoCategoriaGeneralController {
	
	@Autowired
	private ArchivoCategoriaGeneralService aCGService;
	@Autowired
	private ParroquiaService parroquiaService;
	
	private static final String DIRECCION = "/acg/listar";
	
	@GetMapping("/home")
	public String index() {
		return ArchivoCategoriaGeneralView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaACG(@Param("palabra")String palabra) {
		
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.LISTC);
		modelAndView.addObject("url", DIRECCION);
		if (palabra != null) {
			modelAndView.addObject("listaACG", aCGService.encontrarACG(palabra));
		} else {
			modelAndView.addObject("listaACG", aCGService.listarACG());
		}

		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearACG")
	public ModelAndView formularioCrearACG() {
		
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.FORMC);
		modelAndView.addObject("objACG", new ArchivoCategoriaGeneral());
		modelAndView.addObject("listaParroquias", parroquiaService.listarParroquias() );
		return modelAndView;
	}
	
	@PostMapping("/guardarACG")
	public String guardarACG(@ModelAttribute("objACG") ArchivoCategoriaGeneral aCG) {
		
		aCGService.guardarACG(aCG);
		
		return "redirect:/acg/listar";
			
	}
		
	@GetMapping("/formularioActualizarACG/{idACG}")
	public ModelAndView formularioActualizarCuria(@PathVariable("idACG")Long idACG) {
		ModelAndView modelAndView = new ModelAndView(ArchivoCategoriaGeneralView.FORMUPC);
		modelAndView.addObject("objACG", aCGService.buscarPorIdACG(idACG));
		modelAndView.addObject("listaParroquias", parroquiaService.listarParroquias() );
		return modelAndView;
	}
		
	@PostMapping("/actualizarACG")
	public String actualizarACG(@ModelAttribute("objACG") ArchivoCategoriaGeneral aCG) {
		
		aCGService.actualizarACG(aCG);
		
		return "redirect:/acg/listar";
			
	}
	
	@GetMapping("/eliminarACG/{idACG}")
	public String eliminarCuria(@PathVariable("idACG")Long idACG) {
		
		aCGService.eliminarACG(aCGService.buscarPorIdACG(idACG));
		
		return "redirect:/acg/listar";
	}
}
