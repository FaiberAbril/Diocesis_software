package com.sena.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Encargado;

@Repository
public interface EncargadoRepository extends JpaRepository<Encargado, Long>{
	
}
