package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Curia;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.VicariaRepository;

@Service
public class VicariaService {
	
	@Autowired
	private VicariaRepository vicariaRepository;
	
	public void guardarVicaria(Vicaria vicaria) {
		vicariaRepository.save(vicaria);
	}
	
	public void actualizarVicaria(Vicaria vicaria) {
		
		vicariaRepository.save(vicaria);
	}
	
	
	public List<Vicaria> listarVicarias(){
		return vicariaRepository.findAll();
	}
	
	public Page<Vicaria> listarVicarias(Pageable pageable){
		return vicariaRepository.findAll(pageable);
	}
	
	
	public void eliminarVicaria(Vicaria vicaria) {
		vicariaRepository.delete(vicaria);
	}
	
	public Vicaria buscarPorIdVicaria(Long id) {
		return vicariaRepository.findById(id).get();
	}
	
	public Page<Vicaria> encontrarVicariaEspecifica(String palabra,Pageable pg){
		return vicariaRepository.findSpecific(palabra,pg);
	}
	
	public List<Vicaria> buscarPorCuria(Curia curia){
		return vicariaRepository.findByCuria(curia);
	}
}
