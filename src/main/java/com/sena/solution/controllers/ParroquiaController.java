package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.ParroquiaView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.services.ParroquiaService;

@Controller
@RequestMapping("/parroquias")
public class ParroquiaController {
	
	@Autowired
	private ParroquiaService parroquiaservice;

	@GetMapping("/home")
	public String index() {
		return ParroquiaView.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarParroquias() {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.LISTP);
		modelandview.addObject("listaParroquias", parroquiaservice.listarParroquias());
		return modelandview;
	}

	@GetMapping("/formularioParroquias")
	public ModelAndView formularioCrearParroquia() {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.FORMP);
		modelandview.addObject("objParroquia", new Parroquia());
		return modelandview;

	}

	@PostMapping("/guardarParroquia")
	public String guardarParroquia(@ModelAttribute("objParroquia") Parroquia nuevaParroquia) {
		parroquiaservice.guardarParroquia(nuevaParroquia);
		return "redirect:/parroquias/listar";
	}

	@GetMapping("/formularioActualizarParroquia/{idParroquia}")
	public ModelAndView formularioActualizarParroquia(@PathVariable("idParroquia") Long idParroquia) {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.FORMUPP);
		modelandview.addObject("objParroquia", parroquiaservice.buscarPorIdParroquia(idParroquia));
		return modelandview;
	}

	@PostMapping("/actualizarParroquia")
	public String actualizarParroquia(@ModelAttribute("objParroquia") Parroquia parroquia) {
		parroquiaservice.actualizarParroquia(parroquia);
		return "redirect:/parroquias/listar";
	}
	
	@GetMapping("/eliminarParroquuia/{idParroquia}")
	public String eliminarParroquia(@PathVariable("idParroquia") Long idParroquia) {
		parroquiaservice.eliminarParroquia(parroquiaservice.buscarPorIdParroquia(idParroquia));
		return "redirect:/parroquias/";
	}

}
