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
		// TODO Auto-generated constructor stub
	}
	
	public Parroquia(Long id, @NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@Email(message = "El email no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El email no puede estar vacio") String email,
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
