	package com.sena.solution.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.VicariaView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.Vicaria;
import com.sena.solution.services.CuriaService;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.VicariaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vicaria")
public class VicariaController {
	
	
	@Autowired
	private VicariaService vicariaService;
	
	@Autowired
	private ParroquiaService parroquiaService;
	

	@Autowired
	private CuriaService curiaService;
	
	private static final String DIRECCION = "/vicaria/listar"; 
	
	@GetMapping("/home")
	public String index() {
		return VicariaView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaVicaria(@RequestParam(defaultValue = "0")int page,@Param("palabra")String palabra ) {
		ModelAndView modelAndView = new ModelAndView(VicariaView.LISTV);
		modelAndView.addObject("url", DIRECCION);
		modelAndView.addObject("palabra", palabra);
		modelAndView.addObject("currentPage", page);
		Pageable pg = PageRequest.of(page, 4);
		if(palabra != null) {
			modelAndView.addObject("listaVicarias", vicariaService.encontrarVicariaEspecifica(palabra,pg));
		} else {
			modelAndView.addObject("listaVicarias", vicariaService.listarVicarias(pg));
		}
		
		
		//modelAndView.addObject("listaVicariasPaginas", vicariaService.encontrarPaginas(PageRequest.of(page, 4)) );
		
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
	public String guardarVicaria(@Valid @ModelAttribute("objVicaria")Vicaria vicaria, BindingResult br ,Model model) {
		
		if (br.hasErrors()) {
			model.addAttribute("listaCurias", curiaService.listarCurias());
			
			return VicariaView.FORMV;
		}
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
	public String actualizarVicaria(@Valid @ModelAttribute("objVicaria") Vicaria vicaria, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("listaCurias", curiaService.listarCurias());
			
			return VicariaView.FORMUPV;
		}
		
		List<Parroquia> parroquias = vicariaService.buscarPorIdVicaria(vicaria.getIdVicaria()).getParroquias();
		for (Iterator<Parroquia> iterator = parroquias.iterator(); iterator.hasNext();) {
	        Parroquia parroquia = iterator.next();
	        parroquia.setVicaria(vicaria);
	        //iterator.remove(); //remove the child first
		}
		vicaria.setParroquias(parroquias);
		vicariaService.actualizarVicaria(vicaria);
		
		return "redirect:/vicaria/listar";
			
	}
	
	@GetMapping("/eliminarVicaria/{idVicaria}")
	public String eliminarVicaria(@PathVariable("idVicaria")Long idVicaria) {
		
		List<Parroquia> parroquias = vicariaService.buscarPorIdVicaria(idVicaria).getParroquias();
		
		Iterator<Parroquia> iterator = parroquias.iterator();
		while (iterator.hasNext()) {
			
            Parroquia parroquia = iterator.next();
            parroquia.setVicaria(null);
            parroquiaService.actualizarParroquia(parroquia);

        }
		
		vicariaService.eliminarVicaria(vicariaService.buscarPorIdVicaria(idVicaria));
		
		
		
		return "redirect:/vicaria/listar";
	}

	

}
