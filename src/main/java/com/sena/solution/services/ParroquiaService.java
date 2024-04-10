package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Parroquia;
import com.sena.solution.repositories.ParroquiaRepository;

import jakarta.validation.Valid;

@Service
public class ParroquiaService {
	@Autowired
	private ParroquiaRepository parroquiarepository;

	public void guardarParroquia(Parroquia parroquia) {
		parroquiarepository.save(parroquia);
	}

	public void actualizarParroquia(Parroquia parroquia) {
		parroquiarepository.save(parroquia);
	}

	public List<Parroquia> listarParroquias() {
		return parroquiarepository.findAll();
	}
	
	public Page<Parroquia> listarParroquias(Pageable pageable) {
		return parroquiarepository.findAll(pageable);
	}

	public void eliminarParroquia(Parroquia parroquia) {
		parroquiarepository.delete(parroquia);
	}

	public Parroquia buscarPorIdParroquia(Long idParroquia) {
		return parroquiarepository.findById(idParroquia).get();
	}
	
	public Page<Parroquia> encontrarParroquiaEspecifica(String palabra,Pageable pageable){
		return parroquiarepository.findEspecific(palabra, pageable);
	}

}
