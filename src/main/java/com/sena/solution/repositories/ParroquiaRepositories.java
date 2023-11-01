package com.sena.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.solution.models.Parroquia;
@Repository
public interface ParroquiaRepositories extends JpaRepository<Parroquia, Long> {

}
