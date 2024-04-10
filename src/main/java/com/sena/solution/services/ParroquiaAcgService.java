package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Parroquia;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;
import com.sena.solution.repositories.ParroquiaAcgRepository;

@Service
public class ParroquiaAcgService {
  @Autowired
  private ParroquiaAcgRepository parroquiaAcgRepository;

  public void guardarParroquiaAcg(ParroquiaAcg ParroquiaAcg) {
		parroquiaAcgRepository.save(ParroquiaAcg);
	}
	
	public void actualizarParroquiaAcg(ParroquiaAcg ParroquiaAcg) {
		parroquiaAcgRepository.save(ParroquiaAcg);
	}
	
	public List<ParroquiaAcg> listarParroquiaAcg(){
		return parroquiaAcgRepository.findAll();
	}
	
	public void eliminarParroquiaAcg(ParroquiaAcg ParroquiaAcg) {
		parroquiaAcgRepository.delete(ParroquiaAcg);
	}
	
	public ParroquiaAcg buscarPorIdParroquiaAcg(ParroquiaAcgPK id) {
		return parroquiaAcgRepository.findById(id).get();
	}

	public List<ParroquiaAcg> buscarPorParroquia(Parroquia parroquia) {
		return parroquiaAcgRepository.findByParroquia(parroquia);
	}
}
