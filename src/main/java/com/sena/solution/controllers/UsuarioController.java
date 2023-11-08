package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sena.solution.controllers.views.UsuarioView;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ParroquiaService parroquiaService;
	

	@GetMapping("/home")
	public String index() {
		return UsuarioView.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarUsuarios() {
		ModelAndView modelandview = new ModelAndView(UsuarioView.LISTU);
		modelandview.addObject("listaUsuarios", usuarioService.listarUsuarios());
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
	public String guardarUsuario(@ModelAttribute("objUsuario") Usuario nuevoUsuario) {
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
	public String actualizarUsuario(@ModelAttribute("objUsuario") Usuario usuario) {
		usuarioService.actualizarUsuario(usuario);
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/eliminarUsuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable("idUsuario") Long idUsuario) {
		usuarioService.eliminarUsuario(usuarioService.buscarPorIdUsuario(idUsuario));
		return "redirect:/usuario/";
	}
	
}
