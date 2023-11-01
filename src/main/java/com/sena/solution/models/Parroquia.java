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
@Table(name = "Parroquias")
public class Parroquia extends Entidades{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idParroquia;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vicaria vicaria;
	
	public Parroquia() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Parroquia(Long idParroquia,
			@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@NotEmpty(message = "El Email es obligatorio") @Email String email,  Vicaria vicaria) {
		super(nombre, direccion, ciudad, telefono, email);
		this.idParroquia = idParroquia;
		this.vicaria = vicaria;
	}



	public Long getIdParroquia() {
		return idParroquia;
	}
	
	public void setIdParroquia(Long idParroquia) {
		this.idParroquia = idParroquia;
	}



	public Vicaria getVicaria() {
		return vicaria;
	}



	public void setVicaria(Vicaria vicaria) {
		this.vicaria = vicaria;
	}

	
}
