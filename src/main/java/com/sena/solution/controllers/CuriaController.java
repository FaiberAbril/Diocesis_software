package com.sena.solution.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.sena.solution.controllers.views.CuriaView;
import com.sena.solution.models.Curia;
import com.sena.solution.models.Encargado;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.CuriaRepository;
import com.sena.solution.services.CuriaService;
import com.sena.solution.services.EncargadoService;
import com.sena.solution.services.VicariaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/curia")
//@PreAuthorize("hasRole('ADMIN')")
public class CuriaController {
	
	
	@Autowired
	private CuriaService curiaService;
	
	@Autowired
	private VicariaService vicariaService;
	
	@Autowired
	private EncargadoService encargadoService;
	
	private static final String DIRECCION = "/curia/listar"; 
	
	@GetMapping("/home")
	public String index() {
		return CuriaView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaCurias(@RequestParam(defaultValue = "0")int page, @Param("palabra")String palabra) {
		
		
		ModelAndView modelAndView = new ModelAndView(CuriaView.LISTC);
		modelAndView.addObject("url", DIRECCION);
		modelAndView.addObject("palabra", palabra);
		modelAndView.addObject("currentPage", page);
		Pageable pg = PageRequest.of(page, 4);
		if(palabra != null) {
			modelAndView.addObject("listaCurias", curiaService.encontrarCuriaEspecifica(palabra,pg));
		} else {
			modelAndView.addObject("listaCurias", curiaService.listarCurias(pg));
		}
		//modelAndView.addObject("data", c.findAll(PageRequest.of(page, 2)));

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
		
		
		List<Vicaria> listaVicaria = vicariaService.buscarPorCuria(curiaService.buscarPorIdCuria(idCuria));
		List<Encargado> listaEncargado = encargadoService.buscarPorCuria(curiaService.buscarPorIdCuria(idCuria));
		
		
		
		if(!listaVicaria.isEmpty()) {
			Iterator<Vicaria> iteVicaria = listaVicaria.iterator();
			
			while(iteVicaria.hasNext()) {
				Vicaria vicaria = iteVicaria.next();
				vicaria.setCuria(null);
				vicariaService.actualizarVicaria(vicaria);
			}
		}
		
		if(!listaEncargado.isEmpty()) {
			Iterator<Encargado> iteEncargado = listaEncargado.iterator();
			
			while(iteEncargado.hasNext()) {
				Encargado encargado = iteEncargado.next();
				encargado.setCuria(null);
				encargadoService.actualizarEncargado(encargado);
			}
		}
		
		curiaService.eliminarCuria(curiaService.buscarPorIdCuria(idCuria));
		
		return "redirect:/curia/listar";
	}
	
}
