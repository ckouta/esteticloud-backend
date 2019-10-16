package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_horarioProfesional")
public class estado_horarioProfesional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_horarioProfesional;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	public Long getId_estado_horarioProfesional() {
		return id_estado_horarioProfesional;
	}

	public void setId_estado_horarioProfesional(Long id_estado_horarioProfesional) {
		this.id_estado_horarioProfesional = id_estado_horarioProfesional;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
