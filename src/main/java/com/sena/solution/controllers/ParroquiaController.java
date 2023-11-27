package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.ParroquiaView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.VicariaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/parroquias")
public class ParroquiaController {
	
	@Autowired
	private ParroquiaService parroquiaService;
	
	@Autowired
	private VicariaService vicariaService;

	@GetMapping("/home")
	public String index() {
		return ParroquiaView.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarParroquias() {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.LISTP);
		modelandview.addObject("listaParroquias", parroquiaService.listarParroquias());
		return modelandview;
	}

	@GetMapping("/formularioParroquias")
	public ModelAndView formularioCrearParroquia() {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.FORMP);
		modelandview.addObject("objParroquia", new Parroquia());
		modelandview.addObject("listaVicarias", vicariaService.listarVicarias());
		return modelandview;

	}

	@PostMapping("/guardarParroquia")
	public String guardarParroquia(@Valid @ModelAttribute("objParroquia") Parroquia nuevaParroquia, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("listaVicarias", vicariaService.listarVicarias());
			
			return ParroquiaView.FORMP;
		}
		parroquiaService.guardarParroquia(nuevaParroquia);
		return "redirect:/parroquias/listar";
	}

	@GetMapping("/formularioActualizarParroquia/{idParroquia}")
	public ModelAndView formularioActualizarParroquia(@PathVariable("idParroquia") Long idParroquia) {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.FORMUPP);
		modelandview.addObject("objParroquia", parroquiaService.buscarPorIdParroquia(idParroquia));
		modelandview.addObject("listaVicarias", vicariaService.listarVicarias());
		return modelandview;
	}

	@PostMapping("/actualizarParroquia")
	public String actualizarParroquia(@ModelAttribute("objParroquia") Parroquia parroquia) {
		parroquiaService.actualizarParroquia(parroquia);
		return "redirect:/parroquias/listar";
	}
	
	@GetMapping("/eliminarParroquia/{idParroquia}")
	public String eliminarParroquia(@PathVariable("idParroquia") Long idParroquia) {
		parroquiaService.eliminarParroquia(parroquiaService.buscarPorIdParroquia(idParroquia));
		return "redirect:/parroquias/listar";
	}

}
