package com.sena.solution.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "curias")
public class Curia extends Entidades{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCuria;
	
	
	public Curia() {
		// TODO Auto-generated constructor stub
	}

	public Curia(Long idCuria,
			@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@NotEmpty(message = "El Email es obligatorio") @Email String email, Encargado encargado) {
		super(nombre, direccion, ciudad, telefono, email);
		this.idCuria = idCuria;
	}

	public Long getIdCuria() {
		return idCuria;
	}

	public void setIdCuria(Long idCuria) {
		this.idCuria = idCuria;
	}

	
}