package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.CuriaView;
import com.sena.solution.models.Curia;
import com.sena.solution.services.CuriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/curia")
public class CuriaController {
	
	
	@Autowired
	private CuriaService curiaService;
	
	private static final String DIRECCION = "/curia/listar"; 
	
	@GetMapping("/home")
	public String index() {
		return CuriaView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaCurias(@Param("palabra")String palabra) {
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.LISTC);
		modelAndView.addObject("url", DIRECCION);
		if(palabra != null) {
			modelAndView.addObject("listaCurias", curiaService.encontrarCuriaEspecifica(palabra));
		} else {
			modelAndView.addObject("listaCurias", curiaService.listarCurias());
		}
		//modelAndView.addObject("palabra", palabra);
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearCuria")
	public ModelAndView formularioCrearCuria() {
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.FORMC);
		modelAndView.addObject("objCuria", new Curia());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarCuria")
	public String guardarCuria(@Valid @ModelAttribute("objCuria") Curia curia, BindingResult br,Model model) {
		if(br.hasErrors()) {
			return CuriaView.FORMC;
		}
		
		curiaService.guardarCuria(curia);
		
		return "redirect:/curia/listar";
			
	}
		
	@GetMapping("/formularioActualizarCuria/{idCuria}")
	public ModelAndView formularioActualizarCuria(@PathVariable("idCuria")Long idCuria) {
		ModelAndView modelAndView = new ModelAndView(CuriaView.FORMUPC);
		modelAndView.addObject("objCuria", curiaService.buscarPorIdCuria(idCuria));
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarCuria")
	public String actualizarCuria(@Valid @ModelAttribute("objCuria") Curia curia,BindingResult br, Model model) {
		if (br.hasErrors()) {
			return CuriaView.FORMUPC;
		}
		
		curiaService.actualizarCuria(curia);
		
		return "redirect:/curia/listar";
			
	}
	
	@GetMapping("/eliminarCuria/{idCuria}")
	public String eliminarCuria(@PathVariable("idCuria")Long idCuria) {
		
		curiaService.eliminarCuria(curiaService.buscarPorIdCuria(idCuria));
		
		return "redirect:/curia/listar";
	}
}
