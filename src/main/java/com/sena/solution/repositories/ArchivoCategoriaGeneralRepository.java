package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.ArchivoCategoriaGeneral;
import com.sena.solution.models.Parroquia;

@Repository
public interface ArchivoCategoriaGeneralRepository extends JpaRepository<ArchivoCategoriaGeneral, Long>{
	List<ArchivoCategoriaGeneral> findByParroquia(Parroquia parroquia);
}
