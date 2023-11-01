package com.sena.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.solution.models.Parroquia;
import com.sena.solution.repositories.ParroquiaRepository;

public class ParroquiaService {
	@Autowired
	private ParroquiaRepository parroquiarepository;

	public void crearParroquia(Parroquia parroquia) {
		parroquiarepository.save(parroquia);
	}

	public void actualizarParroquia(Parroquia parroquia) {
		parroquiarepository.save(parroquia);
	}

	public List<Parroquia> listarParroquias() {
		return parroquiarepository.findAll();
	}

	public void eliminarParroquia(Parroquia parroquia) {
		parroquiarepository.delete(parroquia);
	}

	public Parroquia buscarParroquia(Long idParroquia) {
		return parroquiarepository.findById(idParroquia).get();
	}

}