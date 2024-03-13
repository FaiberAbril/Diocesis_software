package com.sena.solution.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Curia;
import com.sena.solution.models.Vicaria;

@Repository
public interface VicariaRepository extends JpaRepository<Vicaria, Long> {
	Boolean existsByNombreVicaria(String nombre);
	
	@Query(value = "SELECT v FROM Vicaria v WHERE v.nombreVicaria LIKE %?1%",
			countQuery = "SELECT count(v) FROM Vicaria v WHERE v.nombreVicaria LIKE %?1%")
	public Page<Vicaria> findSpecific(String palabra, Pageable pageble);
	
	public List<Vicaria> findByCuria(Curia curia);
}
