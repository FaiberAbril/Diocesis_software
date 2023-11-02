package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.CuriaView;
import com.sena.solution.models.Curia;
import com.sena.solution.services.CuriaService;

@Controller
@RequestMapping("/curia")
public class CuriaController {
	
	
	@Autowired
	private CuriaService curiaService;
	
	@GetMapping("/home")
	public String index() {
		return CuriaView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaCurias() {
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.LISTC);
		modelAndView.addObject("listaCurias", curiaService.listarCurias());
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearCuria")
	public ModelAndView formularioCrearCuria() {
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.FORMC);
		modelAndView.addObject("ObjCuria", new Curia());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarCuria")
	public String guardarCuria(@ModelAttribute("curia") Curia curia) {
		
		curiaService.guardarCuria(curia);
		
		return "redirect:/curia/listar";
			
	}
		
	@GetMapping("/formularioActualizarCuria/{idCuria}")
	public ModelAndView formularioActualizarCuria(@PathVariable("idCuria")Long idCuria) {
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.FORMUPC);
		modelAndView.addObject("ObjCuria", curiaService.buscarPorIdCuria(idCuria));
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarCuria")
	public String actualizarCuria(@ModelAttribute("curia") Curia curia) {
		
		curiaService.actualizarCuria(curia);
		
		return "redirect:/curia/listar";
			
	}
	
	@GetMapping("/eliminarCuria/{idCuria}")
	public String eliminarCuria(@PathVariable("idCuria")Long idCuria) {
		
		curiaService.eliminarCuria(curiaService.buscarPorIdCuria(idCuria));
		
		return "redirect:/curia/listar";
	}
}
