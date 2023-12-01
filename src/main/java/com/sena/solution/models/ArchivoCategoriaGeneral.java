package com.sena.solution.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "acg")
public class ArchivoCategoriaGeneral{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acg")
	private Long idACG;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Column(name = "nombre_acg")
	private String nombreACG;
	
	public ArchivoCategoriaGeneral() {
		// TODO Auto-generated constructor stub
	}

	public ArchivoCategoriaGeneral(Long idACG, @NotBlank(message = "El nombre es obligatorio") String nombreACG
			) {
		this.idACG = idACG;
		this.nombreACG = nombreACG;
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

}
