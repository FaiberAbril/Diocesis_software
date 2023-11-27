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
	private String tipo;

	@ManyToOne(optional = true)
	@JoinColumns( {
    @JoinColumn(name="id_parroquia", referencedColumnName="id_parroquia"),
    @JoinColumn(name="id_acg", referencedColumnName="id_acg")
	} )
	private ParroquiaAcg parroquiaAcg;


	public Documento() {
	}


	public Documento(Long idDocumento, String nombreDocumento, String path, String tipo, ParroquiaAcg parroquiaAcg) {
		this.idDocumento = idDocumento;
		this.nombreDocumento = nombreDocumento;
		this.path = path;
		this.tipo = tipo;
		this.parroquiaAcg = parroquiaAcg;
	}


	public Long getIdDocumento() {
		return idDocumento;
	}


	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}


	public String getNombreDocumento() {
		return nombreDocumento;
	}


	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public ParroquiaAcg getParroquiaAcg() {
		return parroquiaAcg;
	}


	public void setParroquiaAcg(ParroquiaAcg parroquiaAcg) {
		this.parroquiaAcg = parroquiaAcg;
	}
	
	
}
