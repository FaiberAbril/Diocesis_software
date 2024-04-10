package com.sena.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;

@Repository
public interface ParroquiaAcgRepository extends JpaRepository<ParroquiaAcg,ParroquiaAcgPK> {
  List<ParroquiaAcg> findByParroquia(Parroquia parroquia);
}
