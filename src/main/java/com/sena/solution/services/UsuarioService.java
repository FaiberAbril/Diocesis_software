package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.solution.models.Usuario;
import com.sena.solution.repositories.UsuarioRepository;

public class UsuarioService {
	@Autowired
	private UsuarioRepository usuariorepository;

	public void crearUsuario(Usuario usuario) {
		usuariorepository.save(usuario);
	}

	public void actualizarUsuario(Usuario usuario) {
		usuariorepository.save(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return usuariorepository.findAll();
	}

	public void eliminarUsuario(Usuario usuario) {
		usuariorepository.delete(usuario);
	}

	public Usuario buscarUsuario(Long idUsuario) {
		return usuariorepository.findById(idUsuario).get();
	}

}