package com.estetiCloud.Movimiento;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.estetiCloud.Profesional.Profesional;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "movimiento")
@SQLDelete(sql="UPDATE movimiento SET id_estado_movimiento = 2 where id_movimiento=?")
@Where(clause="id_estado_movimiento != 2")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_movimiento;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	@NotNull
	private Integer valor;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fecha;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_profesional"),
    name = "id_profesional", referencedColumnName = "id_profesional")
	private Profesional profesional;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_movimiento"),
    name = "id_estado_movimiento", referencedColumnName = "id_estado_movimiento")
	private EstadoMovimiento estado_movimiento;
	
	

	public Movimiento(Long id_movimiento, @NotEmpty String nombre, @NotEmpty String descripcion,
			@NotEmpty Integer valor, @NotEmpty LocalDate fecha, Profesional profesional,
			com.estetiCloud.Movimiento.EstadoMovimiento estado_movimiento) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public EstadoMovimiento getEstado_movimiento() {
		return estado_movimiento;
	}

	public void setEstado_movimiento(EstadoMovimiento estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}



}
