package com.estetiCloud.Servicio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_servicio")
public class EstadoServicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_servicio;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	public Long getId_estado_servicio() {
		return id_estado_servicio;
	}

	public void setId_estado_servicio(Long id_estado_servicio) {
		this.id_estado_servicio = id_estado_servicio;
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
