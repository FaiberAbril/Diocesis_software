package com.sena.solution.controllers;

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

import com.sena.solution.controllers.views.UsuarioView;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ParroquiaService parroquiaService;
	
	private static final String DIRRECCION = "/usuario/listar";

	@GetMapping("/home")
	public String index() {
		return UsuarioView.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarUsuarios(@RequestParam(defaultValue = "0")int page, @Param("palabra")String palabra) {
		ModelAndView modelandview = new ModelAndView(UsuarioView.LISTU);
		modelandview.addObject("url", DIRRECCION);
		modelandview.addObject("palabra", palabra);
		modelandview.addObject("currentPage", page);
		Pageable pg = PageRequest.of(page, 4);
		if(palabra != null) {
			modelandview.addObject("listaUsuarios", usuarioService.encontrarUsuario(palabra, pg));
		} else {
			modelandview.addObject("listaUsuarios", usuarioService.listarUsuarios(pg));
		}

		
		return modelandview;
	}

	@GetMapping("/formularioCrearUsuario")
	public ModelAndView formularioCrearUsuario() {
		ModelAndView modelandview = new ModelAndView(UsuarioView.FORMU);
		modelandview.addObject("objUsuario", new Usuario());
		modelandview.addObject("listaParroquias", parroquiaService.listarParroquias());
		modelandview.addObject("listaRol", usuarioService.listarRoles());
		return modelandview;

	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@Valid @ModelAttribute("objUsuario") Usuario nuevoUsuario, BindingResult br, Model model) {
		
		if (br.hasErrors()) {
			model.addAttribute("listaParroquias", parroquiaService.listarParroquias());
			model.addAttribute("listaRol", usuarioService.listarRoles());
			
			return UsuarioView.FORMU;
		}
		
		usuarioService.guardarUsuario(nuevoUsuario);
		return "redirect:/usuario/listar";
	}

	@GetMapping("/formularioActualizarUsuario/{idUsuario}")
	public ModelAndView formularioActualizarUsuario(@PathVariable("idUsuario") Long idUsuario) {
		ModelAndView modelandview = new ModelAndView(UsuarioView.FORMUPU);
		modelandview.addObject("objUsuario", usuarioService.buscarPorIdUsuario(idUsuario));
		modelandview.addObject("listaParroquias", parroquiaService.listarParroquias());
		modelandview.addObject("listaRol", usuarioService.listarRoles()); 
		return modelandview;
	}

	@PostMapping("/actualizarUsuario")
	public String actualizarUsuario(@Valid @ModelAttribute("objUsuario") Usuario usuario, BindingResult br, Model model) {
		
		if (br.hasErrors()) {
			model.addAttribute("listaParroquias", parroquiaService.listarParroquias());
			model.addAttribute("listaRol", usuarioService.listarRoles());
			
			return UsuarioView.FORMUPU;
		}
		
		usuarioService.actualizarUsuario(usuario);
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/eliminarUsuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable("idUsuario") Long idUsuario) {
		usuarioService.eliminarUsuario(usuarioService.buscarPorIdUsuario(idUsuario));
		return "redirect:/usuario/listar";
	}
	
}
