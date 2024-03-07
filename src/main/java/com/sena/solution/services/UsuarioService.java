package com.sena.solution.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.RolUsuario;
import com.sena.solution.models.Usuario;
import com.sena.solution.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuariorepository;

	public void guardarUsuario(Usuario usuario) {
		usuariorepository.save(usuario);
	}

	public void actualizarUsuario(Usuario usuario) {
		usuariorepository.save(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return usuariorepository.findAll();
	}
	
	public Page<Usuario> listarUsuarios(Pageable pageable) {
		return usuariorepository.findAll(pageable);
	}

	public void eliminarUsuario(Usuario usuario) {
		usuariorepository.delete(usuario);
	}

	public Usuario buscarPorIdUsuario(Long idUsuario) {
		return usuariorepository.findById(idUsuario).get();
	}
	
	public List<RolUsuario> listarRoles(){
		return Arrays.asList(RolUsuario.values());	   
	}
	
	public Page<Usuario> encontrarUsuario(String palabra, Pageable pageable){
		return usuariorepository.findSpecific(palabra, pageable);
	}

}

