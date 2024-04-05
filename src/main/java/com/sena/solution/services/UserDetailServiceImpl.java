package com.sena.solution.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Persona;
import com.sena.solution.models.Usuario;
import com.sena.solution.repositories.EncargadoRepository;
import com.sena.solution.repositories.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EncargadoRepository encargadoRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(usuarioRepository.existsByUsername(username)) {
			throw new UsernameNotFoundException(String.format("El nombre de usuario: %s ya existe", username));
		}
			
		Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Nombre de usuario no encontrado"));
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();//usuarioRepository;
		usuario.getRoles()
			.forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));
		
		
		User user = new User(usuario.getUsername(),
				usuario.getPassword(),
				usuario.isEnabled(),
				usuario.isAccountNoExpired(),
				usuario.isCredentialNoExpired(),
				usuario.isAccountNoLocked(),
				authorityList);
		
		return user;
	}
	
}
