package com.sena.solution.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Vicaria;

@Repository
public interface VicariaRepository extends JpaRepository<Vicaria, Long> {
	Boolean existsByNombreVicaria(String nombre);
	
	@Query("SELECT v FROM Vicaria v WHERE v.nombreVicaria LIKE %?1%")
	public List<Vicaria> findSpecific(String palabra);
	
}
