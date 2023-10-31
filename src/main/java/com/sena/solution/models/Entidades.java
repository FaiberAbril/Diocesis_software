package com.sena.solution.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class Entidades {
	
	@NotBlank(message ="El nombre es obligatorio")
	private String nombre;
	

	@NotBlank(message ="La direccion es obligatorio")
	private String direccion;
	

	@NotBlank(message ="La ciudad es obligatorio")
	private String ciudad;
	
	
	@NotBlank(message ="El Telefono es obligatorio")
	private String telefono;
	
	
	@Email(message = "El email no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "El email no puede estar vacio")
	private String email;
	
	public Entidades() {
		// TODO Auto-generated constructor stub
	}

	public Entidades(@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@NotEmpty(message = "El Email es obligatorio") @Email String email) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.email = email;
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
