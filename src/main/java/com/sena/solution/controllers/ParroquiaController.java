package com.sena.solution.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import com.sena.solution.controllers.views.ParroquiaView;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ParroquiaAcgService;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.UsuarioService;
import com.sena.solution.services.VicariaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/parroquias")
public class ParroquiaController {
	
	@Autowired
	private ParroquiaService parroquiaService;
	
	@Autowired
    private ParroquiaAcgService parroquiaAcgService;
	
	@Autowired
	private VicariaService vicariaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static final String DIRRECCION = "/parroquias/listar";

	@GetMapping("/home")
	public String index() {
		return ParroquiaView.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarParroquias(@RequestParam(defaultValue = "0")int page, @Param("palabra")String palabra) {
		ModelAndView modelandview = new ModelAndView(ParroquiaView.LISTP);
		modelandview.addObject("url", DIRRECCION);
		modelandview.addObject("palabra", palabra);
		modelandview.addObject("currentPage", page);
		Pageable pg = PageRequest.of(page, 4);
		if(palabra != null) {
			modelandview.addObject("listaParroquias", parroquiaService.encontrarParroquiaEspecifica(palabra,pg));
		} else {
			modelandview.addObject("listaParroquias", parroquiaService.listarParroquias(pg));
		}
		
		//modelandview.addObject("palabra", palabra);
		
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
	public String actualizarParroquia(@Valid @ModelAttribute("objParroquia") Parroquia parroquia, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("listaVicarias", vicariaService.listarVicarias());
			
			return ParroquiaView.FORMUPP;
		}
		parroquiaService.actualizarParroquia(parroquia);
		return "redirect:/parroquias/listar";
	}
	
	@GetMapping("/eliminarParroquia/{idParroquia}")
	public String eliminarParroquia(@PathVariable("idParroquia") Long idParroquia) {
		
		List<ParroquiaAcg> listaParroquiaACG = parroquiaAcgService.buscarPorParroquia(parroquiaService.buscarPorIdParroquia(idParroquia));
		List<Usuario>  listaUsuario = usuarioService.buscarPorParroquia(parroquiaService.buscarPorIdParroquia(idParroquia));
		
		
		if(!listaParroquiaACG.isEmpty()) {
			eliminarParroquiasAcg(listaParroquiaACG);
		}
		
		if(!listaUsuario.isEmpty()) {
			quitarReferenciaUsuarios(listaUsuario);
		}
		
		parroquiaService.eliminarParroquia(parroquiaService.buscarPorIdParroquia(idParroquia));
		return "redirect:/parroquias/listar";
	}
	
	
	private void eliminarParroquiasAcg(List<ParroquiaAcg> listaParroquiaAcg) {
		Iterator<ParroquiaAcg> iteParroquiaACG= listaParroquiaAcg.iterator();
		while(iteParroquiaACG.hasNext()) {
			ParroquiaAcg parroquiaAcg = iteParroquiaACG.next();
			parroquiaAcgService.eliminarParroquiaAcg(parroquiaAcg);				
		}
	}
	
	private void quitarReferenciaUsuarios(List<Usuario> listaUsuarios) {
		Iterator<Usuario> iteUsuario= listaUsuarios.iterator();
		while(iteUsuario.hasNext()) {
			Usuario usuario = iteUsuario.next();
			usuario.setParroquia(null);
			usuarioService.guardarUsuario(usuario);
		}
	}

}
