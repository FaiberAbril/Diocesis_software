package com.sena.solution.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Parroquia;
import com.sena.solution.models.RolEntity;
import com.sena.solution.models.RolEnum;
import com.sena.solution.models.Usuario;
import com.sena.solution.models.Dto.UsuarioDTO;
import com.sena.solution.repositories.RolRepository;
import com.sena.solution.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@Autowired
	private RolRepository rolRepository;

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
	
	public List<RolEntity> listarRoles(){
		return rolRepository.findAll();	   
	}
	
	public Page<Usuario> encontrarUsuario(String palabra, Pageable pageable){
		return usuariorepository.findSpecific(palabra, pageable);
	}
	
	public List<Usuario> buscarPorParroquia(Parroquia parroquia){
		return usuariorepository.findByParroquia(parroquia);
	}
	
	public boolean existeUsuario(String username) {
		return usuariorepository.existsByUsername(username);
	}

	public boolean existeUsuarioById(Long id) {
		return usuariorepository.existsById(id);
	}
	
	public UsuarioDTO convertirUsuarioToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = null;

		if (usuario != null) {
			usuarioDTO = new UsuarioDTO(usuario.getId(),
					usuario.getNombre(),
					usuario.getApellido(),
					usuario.getCedula(),
					usuario.getTelefono(),
					usuario.getEmail(),
					usuario.getParroquia(),
					usuario.isEnabled(),
					usuario.isAccountNoExpired(),
					usuario.isAccountNoLocked(),
					usuario.isCredentialNoExpired(),
					usuario.getRoles());
		}
		return usuarioDTO;
	}
	
	public Usuario convertirDTOToUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = null;
		if (usuarioDTO != null && existeUsuarioById(usuarioDTO.getId())) {
			usuario = buscarPorIdUsuario(usuarioDTO.getId());
			usuario.setNombre(usuarioDTO.getNombre());
			usuario.setApellido(usuarioDTO.getApellido());
			usuario.setCedula(usuarioDTO.getCedula());
			usuario.setTelefono(usuarioDTO.getTelefono());
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setEnabled(usuarioDTO.getIsEnabled());
			usuario.setAccountNoExpired(usuarioDTO.getAccountNoExpired());
			usuario.setAccountNoLocked(usuarioDTO.getAccountNoLocked());
			usuario.setCredentialNoExpired(usuarioDTO.getCredentialNoExpired());
			usuario.setRoles(usuarioDTO.getRoles());
			usuario.setParroquia(usuarioDTO.getParroquia());
		}
		return usuario;
	}

}

