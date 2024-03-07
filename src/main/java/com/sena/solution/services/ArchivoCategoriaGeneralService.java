package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.ArchivoCategoriaGeneral;
import com.sena.solution.repositories.ArchivoCategoriaGeneralRepository;

@Service
public class ArchivoCategoriaGeneralService {
	
	@Autowired
	private ArchivoCategoriaGeneralRepository aCGRepository;
	
	
	public void guardarACG(ArchivoCategoriaGeneral ACG) {
		aCGRepository.save(ACG);
	}
	
	public void actualizarACG(ArchivoCategoriaGeneral ACG) {
		aCGRepository.save(ACG);
	}
	
	public List<ArchivoCategoriaGeneral> listarACG(){
		return aCGRepository.findAll();
	}
	
	public Page<ArchivoCategoriaGeneral> listarACG(Pageable pageable){
		return aCGRepository.findAll(pageable);
	}
	
	public void eliminarACG(ArchivoCategoriaGeneral ACG) {
		aCGRepository.delete(ACG);
	}
	
	public ArchivoCategoriaGeneral buscarPorIdACG(Long id) {
		return aCGRepository.findById(id).get();
	}
	
	public Page<ArchivoCategoriaGeneral> encontrarACG(String palabra, Pageable pageable){
		return aCGRepository.findSpecific(palabra,pageable);
	}
	
	/*public List<ArchivoCategoriaGeneral> buscarPorParroquia(Parroquia parroquia){
		return aCGRepository.findByParroquia(parroquia);
	}*/
}
