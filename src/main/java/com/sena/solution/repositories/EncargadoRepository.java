package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Encargado;

@Repository
public interface EncargadoRepository extends JpaRepository<Encargado, Long>{
	
	@Query("SELECT e FROM Encargado e WHERE e.nombre Like %?1%")
	public List<Encargado> findEspecific(String palabra);
	
}
