package com.sena.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sena.solution.controllers.views.Usuarioview;
import com.sena.solution.models.Usuario;
import com.sena.solution.services.ParroquiaService;
import com.sena.solution.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioservice;
	@Autowired
	private ParroquiaService parroquiaservice;
	

	@GetMapping("/home")
	public String index() {
		return Usuarioview.HOME;
	}

	@GetMapping("/listar")
	public ModelAndView listarUsuarios() {
		ModelAndView modelandview = new ModelAndView(Usuarioview.LISTU);
		modelandview.addObject("listaUsuarios", usuarioservice.listarUsuarios());
		return modelandview;
	}

	@GetMapping("/formularioUsuario")
	public ModelAndView formularioCrearUsuario() {
		ModelAndView modelandview = new ModelAndView(Usuarioview.FORMU);
		modelandview.addObject("objUsuario", new Usuario());
		modelandview.addObject("listaParroquias", parroquiaservice.listarParroquias());
		modelandview.addObject("listRol", usuarioservice.listarRoles());
		return modelandview;

	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@ModelAttribute("objUsuario") Usuario nuevoUsuario) {
		usuarioservice.guardarUsuario(nuevoUsuario);
		return "redirect:/usuarios/listar";
	}

	@GetMapping("/formularioActualizarUsuario/{idUsuario}")
	public ModelAndView formularioActualizarUsuario(@PathVariable("idUsuario") Long idUsuario) {
		ModelAndView modelandview = new ModelAndView(Usuarioview.FORMUPU);
		modelandview.addObject("objUsuario", usuarioservice.buscarPorIdUsuario(idUsuario));
		modelandview.addObject("listaParroquias", parroquiaservice.listarParroquias());
		modelandview.addObject("listRol", usuarioservice.listarRoles());
		return modelandview;
	}

	@PostMapping("/actualizarUsuario")
	public String actualizarUsuario(@ModelAttribute("objUsuario") Usuario usuario) {
		usuarioservice.actualizarUsuario(usuario);
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/eliminarUsuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable("idUsuario") Long idUsuario) {
		usuarioservice.eliminarUsuario(usuarioservice.buscarPorIdUsuario(idUsuario));
		return "redirect:/usuarios/";
	}
	
}
