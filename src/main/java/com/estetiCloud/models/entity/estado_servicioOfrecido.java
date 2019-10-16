package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_servicioOfrecido")
public class estado_servicioOfrecido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_servicioOfrecido;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	public Long getId_estado_servicioOfrecido() {
		return id_estado_servicioOfrecido;
	}

	public void setId_estado_servicioOfrecido(Long id_estado_servicioOfrecido) {
		this.id_estado_servicioOfrecido = id_estado_servicioOfrecido;
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
