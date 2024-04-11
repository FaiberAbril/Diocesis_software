package com.sena.solution.models;

import io.micrometer.common.lang.Nullable;
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
	
	
	@ManyToOne(fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
	@Nullable
	@JoinColumn(name = "id_vicaria")
	private Vicaria vicaria;

	public Parroquia() {
		
	}
	
	

	public Parroquia(Long id, @NotBlank(message = "{NotBlank.Entidades.Nombre}") String nombre,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String direccion,
			@NotBlank(message = "{NotBlank.Entidades.Direccion}") String ciudad,
			@NotBlank(message = "{NotBlank.Entidades.Telefono}") String telefono,
			@Email(message = "{Email.Entidades.Email}", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "{NotEmpty.Entidades.Email}") String email,
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
	
	@Override
	public String toString() {
		return "id: " +getId()+", Parroquia: " + getNombre() + ", vicaria: "+ getVicaria().getNombreVicaria() ;
	}

}
