package com.sena.solution.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private RolEnum rolEnum;
	
	public RolEntity() {
		
	}
	
	public RolEntity(Long id, RolEnum rolEnum) {
		super();
		this.id = id;
		this.rolEnum = rolEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolEnum getRolEnum() {
		return rolEnum;
	}

	public void setRolEnum(RolEnum rolEnum) {
		this.rolEnum = rolEnum;
	}

	
}
