package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.solution.models.ArchivoCategoriaGeneral;

@Repository
public interface ArchivoCategoriaGeneralRepository extends JpaRepository<ArchivoCategoriaGeneral, Long>{
	
	@Query(value="SELECT a FROM ArchivoCategoriaGeneral a WHERE a.nombreACG LIKE %?1%",
			countQuery = "SELECT count(a) FROM ArchivoCategoriaGeneral a WHERE a.nombreACG LIKE %?1%")
	public Page<ArchivoCategoriaGeneral> findSpecific(String palabra,Pageable pageable);
}
