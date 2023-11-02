package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "vicarias")
public class Vicaria extends Entidades{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idVicaria;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	private Curia curia;
	
	public Vicaria() {
		// TODO Auto-generated constructor stub
	}

	public Vicaria(Long idVicaria,
			@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "La direccion es obligatorio") String direccion,
			@NotBlank(message = "La ciudad es obligatorio") String ciudad,
			@NotBlank(message = "El Telefono es obligatorio") String telefono,
			@NotEmpty(message = "El Email es obligatorio") @Email String email, Curia curia) {
		super(nombre, direccion, ciudad, telefono, email);
		this.idVicaria = idVicaria;
		this.curia = curia;
	}

	public Long getIdVicaria() {
		return idVicaria;
	}

	public void setIdVicaria(Long idVicaria) {
		this.idVicaria = idVicaria;
	}

	public Curia getCuria() {
		return curia;
	}

	public void setCuria(Curia curia) {
		this.curia = curia;
	}
	
}
