package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Curia;
import com.sena.solution.models.Encargado;
import com.sena.solution.repositories.EncargadoRepository;

@Service
public class EncargadoService {
	
	@Autowired
	private EncargadoRepository encargadoRepository;
	
	public void guardarEncargado(Encargado encargado) {
		encargadoRepository.save(encargado);
	}
	
	public void actualizarEncargado(Encargado encargado) {
		encargadoRepository.save(encargado);
	}
	
	public List<Encargado> listarEncargados(){
		return encargadoRepository.findAll();
	}
	
	public Page<Encargado> listarEncargados(Pageable pageable){
		return encargadoRepository.findAll(pageable);
	}
	
	public void eliminarEncargado(Encargado encargado) {
		encargadoRepository.delete(encargado);
	}
	
	public Encargado buscarPorIdEncargado(Long id) {
		return encargadoRepository.findById(id).get();
	}
	
	public Page<Encargado> encontrarEncargadoEspecifico(String palabra,Pageable pageable){
		return encargadoRepository.findEspecific(palabra,pageable);
	}
	
	public List<Encargado> buscarPorCuria(Curia curia){
		return encargadoRepository.findByCuria(curia);
	}
	
}
