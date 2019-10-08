package com.estetiCloud.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="servicio_ofrecido")
public class Servicio_ofrecido {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_servicioOfrecido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_servicioOfrecido"),
    name = "id_estado_servicioOfrecido", referencedColumnName = "id_estado_servicioOfrecido")
	private estado_servicioOfrecido estado_servicioOfrecido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Servicio servicio ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profesional profesional ;

	
	public Servicio_ofrecido(Long id_servicioOfrecido, Servicio servicio, Profesional profesional,
			com.estetiCloud.models.entity.estado_servicioOfrecido estado_servicioOfrecido) {
		super();
		this.id_servicioOfrecido = id_servicioOfrecido;
		this.servicio = servicio;
		this.profesional = profesional;
		this.estado_servicioOfrecido = estado_servicioOfrecido;
	}
	
	public Servicio_ofrecido() {
		
	}

	public Long getId_servicioOfrecido() {
		return id_servicioOfrecido;
	}

	public void setId_servicioOfrecido(Long id_servicioOfrecido) {
		this.id_servicioOfrecido = id_servicioOfrecido;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public estado_servicioOfrecido getEstado_servicioOfrecido() {
		return estado_servicioOfrecido;
	}

	public void setEstado_servicioOfrecido(estado_servicioOfrecido estado_servicioOfrecido) {
		this.estado_servicioOfrecido = estado_servicioOfrecido;
	}
	
	
	
	
	
	
}
