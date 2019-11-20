package com.estetiCloud.ServicioOfrecido;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Servicio.Servicio;

@Entity
@Table(name="servicio_ofrecido")
public class ServicioOfrecido {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_servicioOfrecido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_servicioOfrecido"),
    name = "id_estado_servicioOfrecido", referencedColumnName = "id_estado_servicioOfrecido")
	private EstadoServicioOfrecido estado_servicioOfrecido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Servicio servicio ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profesional profesional ;

	
	public ServicioOfrecido(Long id_servicioOfrecido, Servicio servicio, Profesional profesional,
			com.estetiCloud.ServicioOfrecido.EstadoServicioOfrecido estado_servicioOfrecido) {
		super();
		this.id_servicioOfrecido = id_servicioOfrecido;
		this.servicio = servicio;
		this.profesional = profesional;
		this.estado_servicioOfrecido = estado_servicioOfrecido;
	}
	
	public ServicioOfrecido() {
		
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

	public EstadoServicioOfrecido getEstado_servicioOfrecido() {
		return estado_servicioOfrecido;
	}

	public void setEstado_servicioOfrecido(EstadoServicioOfrecido estado_servicioOfrecido) {
		this.estado_servicioOfrecido = estado_servicioOfrecido;
	}
	
	
	
	
	
	
}
