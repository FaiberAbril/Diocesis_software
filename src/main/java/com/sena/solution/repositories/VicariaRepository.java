package com.sena.solution.repositories;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Vicaria;

@Repository
public interface VicariaRepository extends JpaRepository<Vicaria, Long> {
	Boolean existsByNombreVicaria(String nombre);
}
