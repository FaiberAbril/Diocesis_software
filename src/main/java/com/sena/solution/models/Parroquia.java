package com.sena.solution.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parroquias")
public class Parroquia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idParroquia;
	

	
}
