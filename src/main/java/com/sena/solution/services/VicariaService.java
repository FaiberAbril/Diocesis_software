package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.VicariaRepository;

@Service
public class VicariaService {
	
	@Autowired
	private VicariaRepository vicariaRepository;
	
	public void crearVicaria(Vicaria vicaria) {
		vicariaRepository.save(vicaria);
	}
	
	public void actualizarVicaria(Vicaria vicaria) {
		vicariaRepository.save(vicaria);
	}
	
	public List<Vicaria> listarVicarias(){
		return vicariaRepository.findAll();
	}
	
	public void eliminarCuria(Vicaria vicaria) {
		vicariaRepository.delete(vicaria);
	}
	
	public Vicaria buscarPorIdVicaria(Long id) {
		return vicariaRepository.findById(id).get();
	}
}