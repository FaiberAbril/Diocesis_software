package com.sena.solution.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sena.solution.controllers.views.ArchivoView;
import com.sena.solution.controllers.views.ParroquiaAcgView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;
import com.sena.solution.services.ArchivoCategoriaGeneralService;
import com.sena.solution.services.ParroquiaAcgService;
import com.sena.solution.services.ParroquiaService;

@Controller
@RequestMapping("/parroquiaAcg")
public class ParroquiaAcgController {
	
	@Autowired
    private ParroquiaAcgService parroquiaAcgService;
  
	@Autowired
	private ParroquiaService parroquiaService;

	@Autowired
	private ArchivoCategoriaGeneralService acgService;

	
	@GetMapping("/home")
	public String index() {
		return ParroquiaAcgView.HOME;
	}
	
	@GetMapping("/listar")
	public ModelAndView listaParroquiaAcg() {
		
		ModelAndView modelAndView = new ModelAndView(ParroquiaAcgView.LISTPA);
		modelAndView.addObject("listaParroquiaAcg", parroquiaAcgService.listarParroquiaAcg());
		
		return modelAndView;
		
	}
	
	@GetMapping("/formularioCrearParroquiaAcg")
	public ModelAndView formularioCrearParroquiaAcg() {
		
		ModelAndView modelAndView = new ModelAndView(ParroquiaAcgView.FORMPA);
		modelAndView.addObject("ObjParroquiaAcg", new ParroquiaAcg());
		modelAndView.addObject("listaParroquias", parroquiaService.listarParroquias());
		modelAndView.addObject("listaAcg", acgService.listarACG());
		
		return modelAndView;
	}
	
	@PostMapping("/guardarParroquiaAcg")
	public String guardarParroquiaAcg(@ModelAttribute("ObjParroquiaAcg") ParroquiaAcg parroquiaAcg) {
		
		
		if(parroquiaAcg.getParroquia() != null && parroquiaAcg.getAcg() != null) {
			
			parroquiaAcg.setId(new ParroquiaAcgPK(parroquiaAcg.getParroquia().getId(), parroquiaAcg.getAcg().getIdACG()));
			parroquiaAcgService.guardarParroquiaAcg(parroquiaAcg);
		}
		
		
		return "redirect:/parroquiaAcg/listar";
			
	}
		
	@GetMapping("/formularioActualizarParroquiaAcg/{idParroquia}/acg/{idAcg}")
	public ModelAndView formularioActualizarParroquiaAcg(@PathVariable("idParroquia")Long idparroquia,@PathVariable("idAcg")Long idAcg) {
		
		ModelAndView modelAndView = new ModelAndView(ParroquiaAcgView.FORMUPPA);
		modelAndView.addObject("ObjParroquiaAcg", parroquiaAcgService.buscarPorIdParroquiaAcg(new ParroquiaAcgPK(idparroquia, idAcg)));
		modelAndView.addObject("listaParroquias", parroquiaService.listarParroquias());
		modelAndView.addObject("listaAcg", acgService.listarACG());
		
		return modelAndView;
	}
		
	@PostMapping("/actualizarParroquiaAcg")
	public String actualizarParroquiaAcg(@ModelAttribute("ObjParroquiaAcg") ParroquiaAcg parroquiaAcg) {
		
		parroquiaAcgService.actualizarParroquiaAcg(parroquiaAcg);
		
		return "redirect:/parroquiaAcg/listar";
			
	}
	
    @GetMapping("/eliminarParroquiaAcg/{idParroquia}/acg/{idAcg}")
	public String eliminarEncargado(@PathVariable("idParroquia")Long idParroquia,@PathVariable("idAcg")Long idAcg) {
		
		parroquiaAcgService.eliminarParroquiaAcg(parroquiaAcgService.buscarPorIdParroquiaAcg(new ParroquiaAcgPK(idParroquia, idAcg)));
		return "redirect:/parroquiaAcg/listar";
	}


	@GetMapping("/{idParroquia}")
	public ModelAndView mostrarParroquiaACG(@PathVariable("idParroquia")Long idParroquia){
		
		ModelAndView modelAndView = new ModelAndView(ArchivoView.HOME);
		Parroquia parroquia = parroquiaService.buscarPorIdParroquia(idParroquia);
		modelAndView.addObject("listaParroquiaACG", parroquiaAcgService.buscarPorParroquia(parroquia));
		
		return modelAndView;
	}
	
}
