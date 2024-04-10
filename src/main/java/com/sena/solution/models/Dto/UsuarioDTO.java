package com.sena.solution.models.Dto;

import java.util.HashSet;
import java.util.Set;

import com.sena.solution.models.Parroquia;
import com.sena.solution.models.RolEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UsuarioDTO {

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
  
  private Parroquia parroquia;

	private boolean isEnabled;
	
	private boolean accountNoExpired;
	
	private boolean accountNoLocked;
	
	private boolean credentialNoExpired;
	
  private Set<RolEntity> roles = new HashSet<>();


  public UsuarioDTO(Long id,
      @NotBlank(message = "El nombre es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un nombre correctamente") String nombre,
      @NotBlank(message = "El apellido es obligatorio") @Pattern(regexp = "^[a-zA-ZáéñíóúüÁÉÑÓÚÜ -]*$", message = "No ha ingresado un apellido correctamente") String apellido,
      @NotBlank(message = "La cedula es obligatorio") @Pattern(regexp = "^[0-9]+$", message = "No ha ingresado un número de cedula correctamente, solo números") String cedula,
      @NotBlank(message = "El telefono es obligatorio") @Pattern(regexp = "^[0-9]{10}+$", message = "No ha ingresado un número de telefono correctamente") String telefono,
      @Email(message = "El correo no es correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "El correo no puede estar vacio") String email,
      Parroquia parroquia, boolean isEnabled, boolean accountNoExpired, boolean accountNoLocked,
      boolean credentialNoExpired, Set<RolEntity> roles) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.cedula = cedula;
    this.telefono = telefono;
    this.email = email;
    this.parroquia = parroquia;
    this.isEnabled = isEnabled;
    this.accountNoExpired = accountNoExpired;
    this.accountNoLocked = accountNoLocked;
    this.credentialNoExpired = credentialNoExpired;
    this.roles = roles;
  }

  public String getNombre() {
    return nombre;
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

  public boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public boolean getAccountNoExpired() {
    return accountNoExpired;
  }

  public void setAccountNoExpired(boolean accountNoExpired) {
    this.accountNoExpired = accountNoExpired;
  }

  public boolean getAccountNoLocked() {
    return accountNoLocked;
  }

  public void setAccountNoLocked(boolean accountNoLocked) {
    this.accountNoLocked = accountNoLocked;
  }

  public boolean getCredentialNoExpired() {
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Parroquia getParroquia() {
    return parroquia;
  }


  public void setParroquia(Parroquia parroquia) {
    this.parroquia = parroquia;
  }
  
 

}
