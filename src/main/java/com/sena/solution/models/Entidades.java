package com.sena.solution.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@MappedSuperclass
public class Entidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message ="{NotBlank.Entidades.Nombre}")
	private String nombre;
	

	@NotBlank(message ="{NotBlank.Entidades.Direccion}")
	private String direccion;
	

	@NotBlank(message = "{NotBlank.Entidades.Ciudad}")
	private String ciudad;
	
	
	@NotBlank(message = "{NotBlank.Entidades.Telefono}")
	private String telefono;
	
	
	@Email(message = "{Email.Entidades.Email}", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "{NotEmpty.Entidades.Email}")
	private String email;
	
	public Entidades() {
	}
	
	


	public Entidades(Long id, @NotBlank(message = "{NotBlank.Entidades.Nombre}") String nombre,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String direccion,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String ciudad,
			@NotBlank(message = "{NotBlank.Entidades.Telefono}") String telefono,
			@Email(message = "{Email.Entidades.Email}", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "{NotEmpty.Entidades.Email}") String email) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.email = email;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
