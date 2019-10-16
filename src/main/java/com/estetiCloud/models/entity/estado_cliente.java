package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_cliente")
public class estado_cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_cliente;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	public Long getId_estado_cliente() {
		return id_estado_cliente;
	}

	public void setId_estado_cliente(Long id_estado_cliente) {
		this.id_estado_cliente = id_estado_cliente;
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
