package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

public class ArchivoCategoriaGeneral{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idACG;
	
	@NotBlank(message ="El nombre es obligatorio")
	private String nombreACG;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Parroquia parroquia;

	public ArchivoCategoriaGeneral(Long idACG, @NotBlank(message = "El nombre es obligatorio") String nombreACG,
			Parroquia parroquia) {
		super();
		this.idACG = idACG;
		this.nombreACG = nombreACG;
		this.parroquia = parroquia;
	}

	public Long getIdACG() {
		return idACG;
	}

	public void setIdACG(Long idACG) {
		this.idACG = idACG;
	}

	public String getNombreACG() {
		return nombreACG;
	}

	public void setNombreACG(String nombreACG) {
		this.nombreACG = nombreACG;
	}

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

}
