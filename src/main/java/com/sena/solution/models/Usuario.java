package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Usuarios")
public class Usuario extends Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUsuario;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Parroquia parroquia;
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(Long idUsuario,
			@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "El apellido es obligatorio") String apellido,
			@NotBlank(message = "La cedula es obligatorio") String cedula,
			@NotBlank(message = "El telefono es obligatorio") String telefono,
			@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
			 Parroquia parroquia) {
		super(nombre, apellido, cedula, telefono, email);
		this.idUsuario = idUsuario;
		this.parroquia = parroquia;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	

}
