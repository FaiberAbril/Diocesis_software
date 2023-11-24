package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Parroquias")

public class Parroquia extends Entidades{
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_vicaria")
	private Vicaria vicaria;

	public Parroquia() {
		
	}
	
	public Parroquia(Long id, @NotBlank( ) String nombre,
			@NotBlank() String direccion,
			@NotBlank() String ciudad,
			@NotBlank() String telefono,
			@Email() String email,
			Vicaria vicaria) {
		super(id, nombre, direccion, ciudad, telefono, email);
		this.vicaria = vicaria;
	}

	public Vicaria getVicaria() {
		return vicaria;
	}

	public void setVicaria(Vicaria vicaria) {
		this.vicaria = vicaria;
	}

}
