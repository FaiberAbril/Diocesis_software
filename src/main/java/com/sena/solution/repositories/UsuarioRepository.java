package com.sena.solution.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Parroquia;
import com.sena.solution.models.Usuario; 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="SELECT u FROM Usuario u WHERE"
			+ " CONCAT(u.nombre, u.apellido, u.cedula) "
			+ " LIKE %?1%",
			countQuery = "SELECT count(u) FROM Usuario u WHERE"
					+ " CONCAT(u.nombre, u.apellido, u.cedula) "
					+ " LIKE %?1%"
			)
	public Page<Usuario> findSpecific(String palabra,Pageable pageable);
	
	
	public List<Usuario> findByParroquia(Parroquia parroquia);
	
	public Optional<Usuario> findByUsername(String username);
	
	public boolean existsByUsername(String username);
}
