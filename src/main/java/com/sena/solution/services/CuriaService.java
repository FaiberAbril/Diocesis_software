package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Curia;
import com.sena.solution.repositories.CuriaRepository;

@Service
public class CuriaService {
	
	@Autowired
	private CuriaRepository curiaRepository;
	
	public void guardarCuria(Curia curia) {
		curiaRepository.save(curia);
	}
	
	public void actualizarCuria(Curia curia) {
		curiaRepository.save(curia);
	}
	
	public Page<Curia> listarCurias(Pageable pageable){
		return curiaRepository.findAll(pageable);
	}
	
	public List<Curia> listarCurias(){
		return curiaRepository.findAll();
	}
	
	public void eliminarCuria(Curia curia) {
		curiaRepository.delete(curia);
	}
	
	public Curia buscarPorIdCuria(Long id) {
		return curiaRepository.findById(id).get();
	}
	
	public Page<Curia> encontrarCuriaEspecifica(String palabra,Pageable pg){
		return curiaRepository.findSpecific(palabra, pg);
	}
}
