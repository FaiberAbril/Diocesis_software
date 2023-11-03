package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "vicarias")
public class Vicaria extends Entidades{
	
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	private Curia curia;
	
	public Vicaria() {
		// TODO Auto-generated constructor stub
	}


	public Vicaria(Long id, @NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@Email(message = "El email no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El email no puede estar vacio") String email,
			Curia curia) {
		super(id, nombre, direccion, ciudad, telefono, email);
		this.curia = curia;
	}


	public Curia getCuria() {
		return curia;
	}

	public void setCuria(Curia curia) {
		this.curia = curia;
	}
	
}
