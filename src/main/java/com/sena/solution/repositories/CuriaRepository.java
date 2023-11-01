package com.sena.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Curia;

@Repository
public interface CuriaRepository extends JpaRepository<Curia, Long> {

}
