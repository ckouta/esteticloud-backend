package com.estetiCloud.models.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movimiento")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_movimiento;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	@NotEmpty
	private Integer valor;
	
	@NotEmpty
	private Date fecha;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_profesional"),
    name = "id_profesional", referencedColumnName = "id_profesional")
	private Profesional profesional;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_movimiento"),
    name = "id_estado_movimiento", referencedColumnName = "id_estado_movimiento")
	private estado_movimiento estado_movimiento;
	
	

	public Movimiento(Long id_movimiento, @NotEmpty String nombre, @NotEmpty String descripcion,
			@NotEmpty Integer valor, @NotEmpty Date fecha, Profesional profesional,
			com.estetiCloud.models.entity.estado_movimiento estado_movimiento) {
		super();
		this.id_movimiento = id_movimiento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.fecha = fecha;
		this.profesional = profesional;
		this.estado_movimiento = estado_movimiento;
	}

	public Movimiento() {
		
	}

	public Long getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Long id_movimiento) {
		this.id_movimiento = id_movimiento;
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

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public estado_movimiento getEstado_movimiento() {
		return estado_movimiento;
	}

	public void setEstado_movimiento(estado_movimiento estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}



}
