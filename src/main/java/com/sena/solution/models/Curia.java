package com.sena.solution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "curias")
public class Curia extends Entidades{
	
	
	public Curia() {
		
	}

	public Curia(Long id, @NotBlank(message = "{NotBlank.Entidades.Nombre}") String nombre,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String direccion,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String ciudad,
			@NotBlank(message = "{NotBlank.Entidades.Telefono}") String telefono,
			@Email(message = "{Email.Entidades.Email}", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "{NotEmpty.Entidades.Email}") String email) {
		super(id, nombre, direccion, ciudad, telefono, email);
	}
	
	
	

	
}
