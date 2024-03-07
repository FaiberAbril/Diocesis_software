package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Curia;

@Repository
public interface CuriaRepository extends JpaRepository<Curia, Long> {
	
	@Query(value="SELECT c FROM Curia c WHERE c.nombre LIKE %?1%",
		countQuery = "SELECT count(c) FROM Curia c WHERE c.nombre LIKE %?1%")
	public Page<Curia> findSpecific(String palabra, Pageable pageable);

}
