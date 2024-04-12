package com.sena.solution.models;




import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Encargado")
public class Encargado extends Persona {
	
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEncargado;*/
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Curia curia;
	
	public Encargado() {
		
	}

	public Encargado(Long id,
			@NotBlank(message = "El nombre es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre correctamente") String nombre,
			@NotBlank(message = "El apellido es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un apellido correctamente") String apellido,
			@NotBlank(message = "La cedula es obligatorio") @Pattern(regexp = "^[0-9]+$", message = "No ha ingresado un número de cedula correctamente, solo números") String cedula,
			@NotBlank(message = "El telefono es obligatorio") @Pattern(regexp = "^[0-9]{10}+$", message = "No ha ingresado un número de telefono correctamente") String telefono,
			@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
			@NotBlank(message = "El nombre de usuario es obligatorio") String username,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$", message = "Contraseña Incorrecta") @NotBlank(message = "La contraseña no puede estar vacia") @Size(min = 8, message = "Debe tener como minimo 8 caracteres") String password,
			Set<RolEntity> roles, Curia curia) {
		super(id, nombre, apellido, cedula, telefono, email, username, password, roles);
		this.curia = curia;
	}



	public Curia getCuria() {
		return curia;
	}



	public void setCuria(Curia curia) {
		this.curia = curia;
	}
	
	
}
