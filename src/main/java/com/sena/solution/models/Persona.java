package com.sena.solution.models;

import java.util.HashSet;
import java.util.Set;

import com.sena.solution.models.builder.PersonaBuilder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre correctamente")
	private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un apellido correctamente")
	private String apellido;
	
	@NotBlank(message = "La cedula es obligatorio")
	@Pattern(regexp = "^[0-9]+$", message = "No ha ingresado un número de cedula correctamente, solo números")
	private String cedula;
	
	@NotBlank(message = "El telefono es obligatorio")
	@Pattern(regexp = "^[0-9]{10}+$", message = "No ha ingresado un número de telefono correctamente")
	private String telefono;
	
	@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "El correo no puede estar vacio")
	private String email;
	
	@NotBlank(message = "El nombre de usuario es obligatorio")
	@Column(name = "usuario")
	private String username;
	
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$", message = "Contraseña Incorrecta")
	@NotBlank(message = "La contraseña no puede estar vacia")
	@Size(min = 8, message = "Debe tener como minimo 8 caracteres")
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_enabled")
	private boolean isEnabled;
	
	@Column(name = "account_no_expired")
	private boolean accountNoExpired;
	
	@Column(name = "account_no_locked")
	private boolean accountNoLocked;
	
	@Column(name = "credential_no_expired")
	private boolean credentialNoExpired;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<RolEntity> roles = new HashSet<>();
	
	public Persona() {
		
	}
	
	public Persona(PersonaBuilder builder ){
		this.nombre = builder.getNombre();
		this.apellido = builder.getApellido();
		this.cedula = builder.getCedula();
		this.telefono = builder.getTelefono();
		this.email = builder.getEmail();
		this.isEnabled = builder.isEnabled();
		this.accountNoExpired = builder.isAccountNoExpired();
		this.accountNoLocked = builder.isAccountNoLocked();
		this.credentialNoExpired = builder.isCredentialNoExpired();
		this.roles = builder.getRoles();
	}
	
	public Persona(Long id,
			@NotBlank(message = "El nombre es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre correctamente") String nombre,
			@NotBlank(message = "El apellido es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un apellido correctamente") String apellido,
			@NotBlank(message = "La cedula es obligatorio") @Pattern(regexp = "^[0-9]+$", message = "No ha ingresado un número de cedula correctamente, solo números") String cedula,
			@NotBlank(message = "El telefono es obligatorio") @Pattern(regexp = "^[0-9]{10}+$", message = "No ha ingresado un número de telefono correctamente") String telefono,
			@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
			Set<RolEntity> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.telefono = telefono;
		this.email = email;
		this.roles = roles;
	}
	
	public Persona(Long id,
			@NotBlank(message = "El nombre es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre correctamente") String nombre,
			@NotBlank(message = "El apellido es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un apellido correctamente") String apellido,
			@NotBlank(message = "La cedula es obligatorio") @Pattern(regexp = "^[0-9]+$", message = "No ha ingresado un número de cedula correctamente, solo números") String cedula,
			@NotBlank(message = "El telefono es obligatorio") @Pattern(regexp = "^[0-9]{10}+$", message = "No ha ingresado un número de telefono correctamente") String telefono,
			@Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
			@NotBlank(message = "El nombre de usuario es obligatorio") String username,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$", message = "Contraseña Incorrecta") @NotBlank(message = "La contraseña no puede estar vacia") @Size(min = 8, message = "Debe tener como minimo 8 caracteres") String password,
			boolean isEnabled, boolean accountNoExpired, boolean accountNoLocked, boolean credentialNoExpired,
			Set<RolEntity> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.telefono = telefono;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.accountNoExpired = accountNoExpired;
		this.accountNoLocked = accountNoLocked;
		this.credentialNoExpired = credentialNoExpired;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public boolean isAccountNoExpired() {
		return accountNoExpired;
	}


	public void setAccountNoExpired(boolean accountNoExpired) {
		this.accountNoExpired = accountNoExpired;
	}


	public boolean isAccountNoLocked() {
		return accountNoLocked;
	}


	public void setAccountNoLocked(boolean accountNoLocked) {
		this.accountNoLocked = accountNoLocked;
	}


	public boolean isCredentialNoExpired() {
		return credentialNoExpired;
	}


	public void setCredentialNoExpired(boolean credentialNoExpired) {
		this.credentialNoExpired = credentialNoExpired;
	}

	public Set<RolEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolEntity> roles) {
		this.roles = roles;
	}
	
}
