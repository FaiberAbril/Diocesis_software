package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Parroquia;
@Repository
public interface ParroquiaRepository extends JpaRepository<Parroquia, Long> {
	Boolean existsByNombre(String nombreParroquia);
	
	@Query("SELECT p FROM Parroquia p WHERE p.nombre LIKE %?1%")
	public List<Parroquia> findEspecific(String palabra);
}
