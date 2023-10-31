package com.sena.solution.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Parroquias")
public class Parroquia extends Entidades{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idParroquia;
	
public Parroquia() {
	// TODO Auto-generated constructor stub
}

public Parroquia(Long idParroquia,
		@NotBlank(message = "El nombre es obligatorio") String nombre,
		@NotBlank(message = "La direccion es obligatorio") String direccion,
		@NotBlank(message = "La ciudad es obligatorio") String ciudad,
		@NotBlank(message = "El Telefono es obligatorio") String telefono,
		@NotBlank(message = "El Email es obligatorio") @Email String email) {
	super(nombre, direccion, ciudad, telefono, email);
	// TODO Auto-generated constructor stub
	this.idParroquia=idParroquia;
}

public Long getIdParroquia() {
	return idParroquia;
}

public void setIdParroquia(Long idParroquia) {
	this.idParroquia = idParroquia;
}
	
}
