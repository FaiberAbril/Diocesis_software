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
@Table(name = "Encargado")
public class Encargado extends Persona {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEncargado;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Column(nullable = false)
	private Curia curia;
	
	public Encargado() {
		// TODO Auto-generated constructor stub
	}

	

	public Encargado(Long idEncargado,
			@NotBlank(message = "El nombre es obligatorio") String nombre,
			@NotBlank(message = "El apellido es obligatorio") String apellido,
			@NotBlank(message = "La cedula es obligatorio") String cedula,
			@NotBlank(message = "El telefono es obligatorio") String telefono,
			@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
			Curia curia) {
		super(nombre, apellido, cedula, telefono, email);
		this.idEncargado = idEncargado;
		this.curia = curia;
	}



	public Long getIdEncargado() {
		return idEncargado;
	}

	public void setIdEncargado(Long idEncargado) {
		this.idEncargado = idEncargado;
	}



	public Curia getCuria() {
		return curia;
	}



	public void setCuria(Curia curia) {
		this.curia = curia;
	}
	
	
}
