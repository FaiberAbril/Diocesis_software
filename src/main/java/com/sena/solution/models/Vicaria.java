package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "vicarias")
public class Vicaria{
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idVicaria;
	
	@NotBlank(message ="El nombre es obligatorio")
	@Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre de vicaria correctamente")
	 private String nombreVicaria;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Curia curia;

	public Vicaria() {
		// TODO Auto-generated constructor stub
	}
	

	public Vicaria(Long idVicaria, @NotBlank(message = "El nombre es obligatorio") String nombreVicaria, Curia curia) {
		this.idVicaria = idVicaria;
		this.nombreVicaria = nombreVicaria;
		this.curia = curia;
		
	}


	public Long getIdVicaria() {
		return idVicaria;
	}


	public void setIdVicaria(Long idVicaria) {
		this.idVicaria = idVicaria;
	}


	public String getNombreVicaria() {
		return nombreVicaria;
	}


	public void setNombreVicaria(String nombreVicaria) {
		this.nombreVicaria = nombreVicaria;
	}


	public Curia getCuria() {
		return curia;
	}


	public void setCuria(Curia curia) {
		this.curia = curia;
	}
	
	
	
}
