

package com.sena.solution.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Documento;
import com.sena.solution.models.ParroquiaAcg;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{
  Optional<Documento> findByNombreDocumento(String nombreDocumento);
  boolean existsByNombreDocumento(String nombreDocumento);
  List<Documento> findByParroquiaAcg(ParroquiaAcg parroquiaAcg);
  
  //void DeleteByNombreDocumento(String nombreDocumento);
}
