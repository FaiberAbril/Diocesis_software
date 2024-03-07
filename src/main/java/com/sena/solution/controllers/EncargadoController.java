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
import com.sena.solution.controllers.views.EncargadoView;
import com.sena.solution.models.Encargado;
import com.sena.solution.services.CuriaService;
import com.sena.solution.services.EncargadoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/encargado")
public class EncargadoController {
	
	@Autowired
	private EncargadoService encargadoService;
	
	@Autowired
	private CuriaService curiaService;
	
	private static final String DIRRECCION = "/encargado/listar";
	
	@GetMapping("/home")
	public String index() {
		return EncargadoView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaEncargados(@Param("palabra")String palabra) {
		
		ModelAndView modelAndView = new ModelAndView(EncargadoView.LISTE);
		modelAndView.addObject("url", DIRRECCION);
		
		if(palabra != null) {
			modelAndView.addObject("listaEncargados", encargadoService.encontrarEncargadoEspecifico(palabra));
		} else {
			modelAndView.addObject("listaEncargados", encargadoService.listarEncargados());
		}
		
		
		
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
	public String guardarEncargado(@Valid @ModelAttribute("ObjEncargado") Encargado encargado, BindingResult br, Model model) {
		
		if (br.hasErrors()) {
			model.addAttribute("listaCurias", curiaService.listarCurias());
			
			return EncargadoView.FORME;
		}
		
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
	public String actualizarEncargado(@Valid @ModelAttribute("ObjEncargado") Encargado encargado, BindingResult br, Model model) {
		
		if (br.hasErrors()) {
			model.addAttribute("listaCurias", curiaService.listarCurias());
			
			return EncargadoView.FORMUPE;
		}
		encargadoService.actualizarEncargado(encargado);
		
		return "redirect:/encargado/listar";
			
	}
	
	@GetMapping("/eliminarEncargado/{idEncargado}")
	public String eliminarEncargado(@PathVariable("idEncargado")Long idEncargado) {
		
		encargadoService.eliminarEncargado(encargadoService.buscarPorIdEncargado(idEncargado));
		
		return "redirect:/encargado/listar";
	}

	
}
