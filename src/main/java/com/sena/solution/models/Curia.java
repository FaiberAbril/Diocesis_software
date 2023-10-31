package com.sena.solution.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curias")
public class Curia extends Entidades{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCuria;
	
	
	
}
