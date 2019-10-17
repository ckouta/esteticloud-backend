package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_bloque")
public class Estado_bloque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_bloque;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	public Long getId_estado_bloque() {
		return id_estado_bloque;
	}

	public void setId_estado_bloque(Long id_estado_bloque) {
		this.id_estado_bloque = id_estado_bloque;
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
