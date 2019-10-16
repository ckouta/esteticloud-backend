package com.estetiCloud.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="servicio")
public class Servicio implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_servicio;
	@NotEmpty
	private String nombre;
	@NotNull
	private Integer duracion;
	@NotNull
	private Integer precio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_servicio"),
    name = "id_estado_servicio", referencedColumnName = "id_estado_servicio")
	private estado_servicio estado_servicio ;

	
	public Servicio() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public estado_servicio getEstado_servicio() {
		return estado_servicio;
	}

	public void setEstado_servicio(estado_servicio estado_servicio) {
		this.estado_servicio = estado_servicio;
	}
	
}
