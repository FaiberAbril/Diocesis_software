package com.sena.solution.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentos")
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDocumento;

	private String nombreDocumento;
	private String path;

	@ManyToOne(optional = false)
	@JoinColumns( {
    @JoinColumn(name="id_parroquia", referencedColumnName="id_parroquia"),
    @JoinColumn(name="id_acg", referencedColumnName="id_acg")
	} )
	private ParroquiaAcg parroquiaAcg;


	
}
